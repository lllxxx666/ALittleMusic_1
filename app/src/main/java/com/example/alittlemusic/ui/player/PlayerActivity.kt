package com.example.alittlemusic.ui.player

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.alittlemusic.databinding.ActivityPlayerBinding
import com.example.alittlemusic.data.Adapter.PlayerInfo
import com.example.alittlemusic.data.logic.model.Song
import com.example.alittlemusic.util.toast
import com.google.android.exoplayer2.util.Util
import com.lzx.starrysky.OnPlayProgressListener
import com.lzx.starrysky.SongInfo
import com.lzx.starrysky.StarrySky
import com.lzx.starrysky.control.RepeatMode
import com.lzx.starrysky.manager.PlaybackStage
import com.lzx.starrysky.manager.PlaybackStage.Companion.PLAYING
import com.lzx.starrysky.utils.formatTime

private const val TAG = "PlayerActivity"

class PlayerActivity : AppCompatActivity() {

    private val binding by lazy (LazyThreadSafetyMode.NONE){ ActivityPlayerBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this).get(PlyerViewModel::class.java)}

    private var songId: Int? = 0
    private var songList = mutableListOf<SongInfo>()
    private var songInfo: SongInfo? = null

    private var rotationAnim: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val playerInfo = intent.getSerializableExtra("playerInfo") as  PlayerInfo

        songId = playerInfo.playList[playerInfo.position].id
        viewModel.playList = playerInfo.playList as ArrayList<Song>
        val song:Song = playerInfo.playList[playerInfo.position]
        //        简单播放一首歌曲
        val info = SongInfo()
        info.songId = songId.toString()
        info.songUrl = "http://music.163.com/song/media/outer/url?id=${songId}.mp3"
        info.songName = song.name
        info.artist = song.ar.map {it.name }.joinToString(separator=" / ")
        info.songCover = song.al.picUrl
        songInfo = info

        initDetailUI(songInfo!!)
        songList = playerInfo.playList.map { it ->
            val info = SongInfo()
            info.songId = songId.toString()
            info.songUrl = "http://music.163.com/song/media/outer/url?id=${songId}.mp3"
            info.songName = song.name
            info.artist = song.ar.map {it.name }.joinToString(separator=" / ")
            info.songCover = song.al.picUrl
            info
        }.toMutableList()

//        歌名和歌手超出一行 滑动显示
        binding.playerSinger.movementMethod= ScrollingMovementMethod.getInstance()
        binding.playerTitle.movementMethod= ScrollingMovementMethod.getInstance()

        StarrySky.with().playMusic(songList,playerInfo.position)

        //状态监听
        StarrySky.with().playbackState().observe(this, { it ->
//            toast("状态改变")
            if (it.stage == PlaybackStage.IDLE && !it.isStop){
                //重播
                StarrySky.with()
                    .skipMediaQueue(true)
                    .playMusic(songList,playerInfo.position)
            }
            when (it.stage) {
                PLAYING -> {
                    binding.exoPlay.visibility = View.GONE
                    binding.exoPause.visibility = View.VISIBLE
                }
                PlaybackStage.SWITCH -> { //切歌
                    it.songInfo?.let {
                        initDetailUI(it)
                    }
                }

                PlaybackStage.PAUSE,
                PlaybackStage.IDLE -> {
                    binding.exoPlay.visibility = View.VISIBLE
                    binding.exoPause.visibility = View.GONE
                }

                PlaybackStage.ERROR -> {
                    binding.exoPlay.visibility = View.VISIBLE
                    binding.exoPause.visibility = View.GONE
                    toast("播放失败：" + it.errorMsg)
                }
            }
        })
        val seekBar = binding.seekbar
        //进度监听
        StarrySky.with().setOnPlayProgressListener(object : OnPlayProgressListener{
            override fun onPlayProgress(currPos: Long, duration: Long) {
                if (seekBar.max.toLong() != duration){
                    seekBar.max = duration.toInt()
                }
                seekBar.progress = currPos.toInt()
                binding.curTime.text = currPos.formatTime()
                binding.endTime.text = duration.formatTime()
            }
        })
        //进度SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                StarrySky.with().seekTo(seekBar.progress.toLong(), true)
            }
        })

        //播放模式
        val repeatMode = StarrySky.with().getRepeatMode()
        when(repeatMode.repeatMode){
            //  列表循环
            RepeatMode.REPEAT_MODE_NONE -> binding.exoRepeat.setImageResource(com.google.android.exoplayer2.ui.R.drawable.exo_controls_repeat_all)
            //  单曲循环
            RepeatMode.REPEAT_MODE_ONE -> binding.exoRepeat.setImageResource(com.google.android.exoplayer2.ui.R.drawable.exo_controls_repeat_one)
            //  随机播放
            RepeatMode.REPEAT_MODE_SHUFFLE -> binding.exoRepeat.setImageResource(com.google.android.exoplayer2.ui.R.drawable.exo_controls_shuffle_on)
        }
        //点击逻辑:列表循环->随机播放->单曲循环->列表循环
        binding.exoRepeat.setOnClickListener {
            val model = StarrySky.with().getRepeatMode()
            when(model.repeatMode){
                RepeatMode.REPEAT_MODE_NONE -> {
                    StarrySky.with().setRepeatMode(RepeatMode.REPEAT_MODE_SHUFFLE, false)
                    binding.exoRepeat.setImageResource(com.google.android.exoplayer2.ui.R.drawable.exo_controls_shuffle_on)
                    toast("随机播放")
                }
                RepeatMode.REPEAT_MODE_ONE -> {
                    StarrySky.with().setRepeatMode(RepeatMode.REPEAT_MODE_NONE, true)
                    binding.exoRepeat.setImageResource(com.google.android.exoplayer2.ui.R.drawable.exo_controls_repeat_all)
                    toast("列表循环")
                }
                RepeatMode.REPEAT_MODE_SHUFFLE -> {
                    StarrySky.with().setRepeatMode(RepeatMode.REPEAT_MODE_ONE, true)
                    binding.exoRepeat.setImageResource(com.google.android.exoplayer2.ui.R.drawable.exo_controls_repeat_one)
                    toast("单曲循环")
                }
            }
        }

        //下一首
        binding.exoNext.setOnClickListener {
            if (StarrySky.with().isSkipToNextEnabled()){
                StarrySky.with().skipToNext()
            }else toast("已经是最后一首了")

        }
        //上一首
        binding.exoPrev.setOnClickListener {
            if (StarrySky.with().isSkipToPreviousEnabled()) StarrySky.with().skipToPrevious()
            else toast("当前歌曲是第一首")
        }
        //播放
        binding.exoPlay.setOnClickListener {
            StarrySky.with().restoreMusic()
            rotationAnim?.resume()
        }
        //暂停
        binding.exoPause.setOnClickListener {
            StarrySky.with().pauseMusic()
            rotationAnim?.pause()

        }
        //播放列表
        binding.exoQueue.setOnClickListener {
            toast("展示播放列表")
        }


    }

    private fun initDetailUI(song: SongInfo) {
        binding.playerTitle.text = song.songName
        binding.playerSinger.text = song.artist
        Glide.with(this).load(song.songCover).into(binding.playerImg)

        rotationAnim = ObjectAnimator.ofFloat(binding.playerImg, "rotation", 0f, 359f)
        rotationAnim?.interpolator = LinearInterpolator()
        rotationAnim?.duration = 20000
        rotationAnim?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                rotationAnim?.start()
            }
        })
        rotationAnim?.start()
    }



    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
           // initializePlayer()

        }
    }

    override fun onPause() {
        super.onPause()
        rotationAnim?.pause()
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        StarrySky.setIsOpenNotification(true)
        if (StarrySky.with().isPlaying()) {
            val info = StarrySky.with().getNowPlayingSongInfo()
            if (info?.tag == "home") {
                rotationAnim?.start()
            }
        }
        rotationAnim?.resume()
    }
    override fun onDestroy() {
        super.onDestroy()
        rotationAnim?.cancel()
        rotationAnim?.removeAllListeners()
        rotationAnim = null
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    companion object {
        fun actionStart(context: Context, obj: PlayerInfo){
            val intent = Intent(context, PlayerActivity::class.java)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("playerInfo",obj)
            context.startActivity(intent)
        }
    }
}