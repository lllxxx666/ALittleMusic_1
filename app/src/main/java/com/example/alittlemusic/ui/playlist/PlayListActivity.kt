package com.example.alittlemusic.ui.playlist

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.activitytest.BaseActivity
import com.example.activitytest.isDarkTheme
import com.example.alittlemusic.R
import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.data.Adapter.PlayListAdapter
import com.example.alittlemusic.data.Adapter.PlayListInfo
import com.example.alittlemusic.data.logic.model.Song
import com.example.alittlemusic.databinding.ActivityPlaylistBinding
import com.example.alittlemusic.util.toast
import nd.no.xww.bottomnavigationlayout.asBitmap
import java.text.SimpleDateFormat


class PlayListActivity : BaseActivity() {

    private lateinit var binding: ActivityPlaylistBinding
    private val vm by lazy { ViewModelProvider(this).get(PlayListViewModel::class.java) }

    companion object{
        fun actionStart(context: Context, from: String ,id: Number, obj: PlayListInfo){
            val intent = Intent(context, PlayListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("from",from)
            intent.putExtra("playListId",id)
            intent.putExtra("playListInfo",obj)
            context.startActivity(intent)

        }
        lateinit var listId : Number

    }
    lateinit var playListInfo :PlayListInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.listToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.listToolbar.setNavigationOnClickListener {
            finish()
        }

        playListInfo = intent.getSerializableExtra("playListInfo") as PlayListInfo
        binding.playlistTitle.text = playListInfo.title
        binding.listCount.text = "( ${playListInfo.trackCount} )"

//        控制不同的布局
        intent.getStringExtra("from").apply {
            when(this){
                "TopList" -> {
                    binding.topPlaylistTitle.text = playListInfo.title
                    binding.topPlaylistSub.visibility = View.VISIBLE
                    binding.topPlaylistTitle.visibility = View.VISIBLE
                    isCollected()
                    getImg()
                }
                "RPlayList" -> {
                    binding.playlistImg.visibility = View.VISIBLE
                    binding.playlistTitle.visibility = View.VISIBLE
                    isCollected()
                    getImg()
                }
                "dailySongs" -> {
                    binding.playlistHead.background = getDrawable(R.drawable.daily_songs_bg)
                    binding.listCount.visibility = View.GONE
                    val month = System.currentTimeMillis().let {
                        SimpleDateFormat("MM").format(it)
                    }
                    val day = System.currentTimeMillis().let {
                        SimpleDateFormat("dd").format(it)
                    }
                    binding.dailyPlaylistDay.text = day
                    binding.dailyPlaylistMm.text = "/$month"
                    binding.dailyPlaylistDay.visibility = View.VISIBLE
                    binding.dailyPlaylistMm.visibility = View.VISIBLE
                    binding.dailyPlaylistSub.visibility = View.VISIBLE
                }

            }
        }

//        加载歌单的歌曲列表
        listId = intent.getLongExtra("playListId", 6952220954)
        vm.playListLiveData.observe(this, Observer { result ->
            val list = result.getOrNull()
            if (list != null){
                vm.playList = list as ArrayList<Song>
                showPlayList()
            }else{
                Toast.makeText(this, "无法成功获取歌单信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = binding.mPlayList
        recyclerView.layoutManager = layoutManager

        vm.collectLiveData.observe(this, Observer { result ->
            val data = result.getOrNull()
            if (data?.code == 200){
               toast(data.message)
                MyApplication.CollectedList.add(playListInfo.id!!)
            }else{
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }

    private fun getImg() {
        Glide.with(this).load(playListInfo.img).into(binding.playlistImg)
//        使用Palette获取图片的色调，并 设置为背景色
        playListInfo.img?.let {
            asBitmap(it) { bitmap ->
                Palette.from(bitmap).generate { palette ->
                    var vibrant: Palette.Swatch?
                    if (isDarkTheme(this)) vibrant= palette?.darkMutedSwatch
                    else vibrant= palette?.lightMutedSwatch
                    if (vibrant != null) {
                        binding.playlistHead.setBackgroundColor((vibrant.rgb))
                        binding.playlistHead.setStatusBarForegroundColor(vibrant.rgb)
                    }
                }
            }
        }
    }
    fun isCollected(){
        //        歌单是否已被收藏
        val collectdList = MyApplication.CollectedList
        val isC = playListInfo.id in collectdList
        binding.collectListBtn.apply {
            this.visibility = View.VISIBLE
            if (isC) {  // 已收藏状态，取消收藏
                text = getString(R.string.collected)
                this.setOnClickListener {
                    vm.collect(2)
                }
            }
            else this.setOnClickListener {
                vm.collect(1)
                text = getString(R.string.collected)
            }
        }
    }
    fun showPlayList(){
        binding.mPlayList.visibility = View.VISIBLE
        binding.plistLoading.visibility = View.GONE

        val adapter = PlayListAdapter(vm.playList)
        binding.mPlayList.adapter = adapter
    }
}