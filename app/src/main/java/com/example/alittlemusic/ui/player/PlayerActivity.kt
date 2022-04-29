package com.example.alittlemusic.ui.player

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.alittlemusic.R
import com.example.alittlemusic.databinding.ActivityPlayerBinding
import com.example.alittlemusic.data.Adapter.PlayerInfo
import com.example.alittlemusic.ui.playlist.PlayListActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import com.lzx.starrysky.SongInfo
import com.lzx.starrysky.StarrySky

private const val TAG = "PlayerActivity"

class PlayerActivity : AppCompatActivity() {

    lateinit var playerBind: PlayerService.PlayerBind   //后期改到 manager 中

    private val  connection = object :ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            playerBind = service as PlayerService.PlayerBind
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d("MyService", "onServiceDisconnected")
        }

    }
    private val binding by lazy (LazyThreadSafetyMode.NONE){
        ActivityPlayerBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy { ViewModelProvider(this).get(PlyerViewModel::class.java)}

    private val playbackStateListener: Player.Listener = playbackStateListener()
    private  var player: ExoPlayer? =null

    private var playWhenReady = false    //播放/暂停状态
    private var currentWindow = 0       //当前播放位置
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val intent = Intent(this,PlayerService::class.java)
//        bindService(intent,connection,Context.BIND_AUTO_CREATE)



        val list = intent.getSerializableExtra("playerInfo")
        Log.d("test", "ss"+list.toString())
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
           // initializePlayer()

            //        简单播放一首歌曲
            val info = SongInfo()
            info.songId = "111"
            info.songUrl = "http://music.163.com/song/media/outer/url?id=317151.mp3"
            StarrySky.with().playMusicByInfo(info)
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
           // initializePlayer()
        }
    }
    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }


    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun initializePlayer(){
        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }

        val playerInfo = intent.getSerializableExtra("playerInfo") as PlayerInfo
Log.d("test",playerInfo.position.toString())
//        val data = getSharedPreferences("data",Context.MODE_PRIVATE)

        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer

                val mediaItem = MediaItem.Builder()
                    .setUri(getString(R.string.media_url_mp3))
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()
//                playerInfo.playList.forEach { song ->
//                    MediaSource.Factory
////                    exoPlayer.addMediaSource()
//                }
                exoPlayer.setMediaItem(mediaItem)
            //    exoPlayer.addMediaSource()  //接受媒体源
                exoPlayer.playWhenReady = playWhenReady     //playWhenReady告诉播放器是否在获取所有播放资源后立即开始播放
                exoPlayer.seekTo(currentWindow,playbackPosition)    //seekTo告诉玩家在特定窗口内寻找特定位置\
                exoPlayer.repeatMode = Player.REPEAT_MODE_ALL   //整个播放列表在无限循环中重复
                //                exoPlayer.shuffleModeEnabled = true     //控制播放列表随机播放
                exoPlayer.addListener(playbackStateListener)    //在准备播放之前注册监听器
                exoPlayer.prepare()     //prepare告诉播放器获取播放所需的所有资源

            }

    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex     //当前窗口索引
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)   //删除监听器releasePlayer
            release()
        }
        player = null
    }
    private fun playbackStateListener() = object : Player.Listener {
        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            super.onTimelineChanged(timeline, reason)
            if (reason == Player.TIMELINE_CHANGE_REASON_PLAYLIST_CHANGED){
                //updateUiForPlaylist(timeline)
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            val play : ImageButton = findViewById(com.google.android.exoplayer2.ui.R.id.exo_play)
            val pause : ImageButton = findViewById(com.google.android.exoplayer2.ui.R.id.exo_pause)
            if (player?.isPlaying == true){
                play.visibility = View.GONE
                pause.visibility = View.VISIBLE
            }else{
                play.visibility = View.VISIBLE
                pause.visibility = View.GONE
            }

//            val stateString: String = when (playbackState) {
//                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE-"
//                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING-"
//                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY-"
//                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED-"
//                else -> "UNKNOWN_STATE-"
//            }
//            Log.d(TAG, "changed state to $stateString")
        }
    }
    companion object {
//        fun actionStart(context: Context, playList : List<PlayListResponse.Song>, position : Int){
        fun actionStart(context: Context, obj: PlayerInfo){
            val intent = Intent(context, PlayerActivity::class.java)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("playerInfo",obj)
            context.startActivity(intent)
        }
    }
}