package com.example.alittlemusic.data.media

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.content.ContextCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.alittlemusic.R
import com.example.alittlemusic.data.media.library.PackageValidator
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.PlaybackPreparer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import kotlinx.coroutines.*

/**
 *
 * @author liuxin
 * Created on 2022/4/20
 * @description：---
 */
class MusicService : MediaBrowserServiceCompat(){

    private lateinit var notificationManager: MNotificationManager
    private lateinit var mediaSource: MediaSource
    private lateinit var packageValidator: PackageValidator

    private lateinit var currentPlayer: Player

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    protected lateinit var mediaSession: MediaSessionCompat
    protected lateinit var mediaSessionConnector: MediaSessionConnector
    private var currentPlaylistItems: List<MediaMetadataCompat> = emptyList()
    private var currentMediaItemIndex: Int = 0



    private var isForegroundService = false

    private val remoteJsonSource: Uri =
        Uri.parse("https://storage.googleapis.com/uamp/catalog.json")

    private val mAudioAttributes = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    private val playerListener = PlayerEventListener()

    private inner class PlayerEventListener : Player.Listener{
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            when(playbackState) {
                Player.STATE_BUFFERING,
                    Player.STATE_READY -> {}

            }
        }
    }
//  配置ExoPlayer处理音频焦点
    private val exoPlayer: ExoPlayer by lazy {
        ExoPlayer.Builder(this).build().apply {
            setAudioAttributes(mAudioAttributes,true)
            setHandleAudioBecomingNoisy(true)
            addListener(playerListener)
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

//        构建可用于启动 UI 的 PendingIntent
        val sessionActivityPendingIntent =
            packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
                PendingIntent.getActivity(this,0,sessionIntent,0)
            }

//            创建一个新的MediaSession
        mediaSession = MediaSessionCompat(this,"MusicService")
            .apply {
                setSessionActivity(sessionActivityPendingIntent)
                isActive = true
            }

//        会话令牌，不一定用它，但是要用必须在MediaBrowserServiceCompat.onGetRoot返回时，不然系统会静默失败
        sessionToken = mediaSession.sessionToken

        /*
        * 通知管理器将使用我们的播放器和媒体会话来决定何时发布通知。
        * 当通知发布或删除时，我们的监听器将被调用，这允许我们将服务提升到前台（
        * 如果主 UI 不可见，我们不会被杀死）。
        * */
        notificationManager = MNotificationManager(
            this,
            mediaSession.sessionToken,
            PlayerNotificationListener()
        )

//        下载相关
//        mediaSource = JsonSource(source = remoteJsonSource)
//        serviceScope.launch {
//            mediaSource.load()
//        }
        notificationManager.showNotificationForPlayer(currentPlayer)
        packageValidator = PackageValidator(this, R.xml.allowed_media_browser_callers)

    }



    //    监听通知事件
    private inner class PlayerNotificationListener :
            PlayerNotificationManager.NotificationListener{
        override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
    //        super.onNotificationCancelled(notificationId, dismissedByUser)
            stopForeground(true)
            isForegroundService = false
            stopSelf()
        }

        override fun onNotificationPosted(
            notificationId: Int,
            notification: Notification,
            ongoing: Boolean
        ) {
    //        super.onNotificationPosted(notificationId, notification, ongoing)
            if (ongoing && !isForegroundService) {
                ContextCompat.startForegroundService(
                    applicationContext,
                    Intent(applicationContext,this@MusicService.javaClass)
                )
                startForeground(notificationId,notification)
                isForegroundService = true
            }
        }
}

    override fun onDestroy() {
        mediaSession.run {
            isActive = false
            release()
        }

//        当服务消失时取消协程。
        serviceJob.cancel()

        exoPlayer.removeListener(playerListener)
        exoPlayer.release()
    }

    //    返回客户端应请求获取要浏览播放的 [MediaItem] 列表的“根”媒体 ID。
    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        val isKnownCaller = packageValidator.isKnownCaller(clientPackageName, clientUid)
        val rootExtras = Bundle().apply {
            putBoolean(
                MEDIA_SEARCH_SUPPORTED,
                isKnownCaller
            )
        }
        return  BrowserRoot(UAMP_EMPTY_ROOT, rootExtras)
    }

//    返回（通过 [result] 参数）作为提供的 [parentMediaId] 的子项的 [MediaItem] 列表。
    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
//        if (parentId == UAMP_RECENT_ROOT) {
//            result.sendResult(storage.loadRecentSong()?.let { song -> listOf(song) })
//        }else{
//            val resultsSent = mediaSource.whenReady { successfullyInitialized ->
//                if (successfullyInitialized) {
//                    val children = browseTree[parentMediaId]?.map { item ->
//                        MediaBrowserCompat.MediaItem(item.description, item.flag)
//                    }
//                    result.sendResult(children)
//                } else {
//                    mediaSession.sendSessionEvent(NETWORK_FAILURE, null)
//                    result.sendResult(null)
//                }
//            }
//
//            if (!resultsSent) {
//                result.detach()
//            }
//        }
}

}

const val UAMP_BROWSABLE_ROOT = "/"
const val UAMP_EMPTY_ROOT = "@empty@"
const val UAMP_RECOMMENDED_ROOT = "__RECOMMENDED__"
const val UAMP_ALBUMS_ROOT = "__ALBUMS__"
const val UAMP_RECENT_ROOT = "__RECENT__"

const val MEDIA_SEARCH_SUPPORTED = "android.media.browse.SEARCH_SUPPORTED"

const val RESOURCE_ROOT_URI = "android.resource://com.example.android.uamp.next/drawable/"
