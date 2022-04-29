package com.example.alittlemusic.ui.player

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.alittlemusic.MainActivity
import com.example.alittlemusic.R
import com.scwang.smart.refresh.layout.listener.OnMultiListener

private const val MY_MEDIA_ROOT_ID = "media_root_id"
private const val MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id"

class PlayerService : Service() {

    private var mediaSession: MediaSessionCompat? = null
    private lateinit var stateBuilder: PlaybackStateCompat.Builder
    private val mBinder = PlayerBind()

    inner class PlayerBind :Binder(){
        public fun bindServiceImple(client: OnMultiListener) {
            bindServiceImple(client)
        }
//        fun OnMusicClient bindServiceImple(){
//            return clientImpl;
//        }
    }

    override fun onBind(intent: Intent): IBinder {
        return  mBinder;
    }

    override fun onCreate() {
        super.onCreate()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("Player_Service","前台播放音乐",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        val notification = NotificationCompat.Builder(this, "Player_Service")
            .setContentText("歌名")
            .setContentText("后面换成歌曲")
            .setSmallIcon(R.drawable.ic_music)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.test_1))
            .setContentIntent(pi)
            .build()
        startForeground(1,notification) //成为前台Serivec,在状态栏显示
    }
}