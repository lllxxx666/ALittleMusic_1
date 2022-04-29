package com.example.alittlemusic.ui.recentPlay

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.example.activitytest.BaseActivity
import com.example.alittlemusic.R
import com.example.alittlemusic.databinding.ActivityRecentPlayBinding
import com.example.alittlemusic.data.Adapter.PlayListAdapter
import com.example.alittlemusic.data.logic.model.RecentPlayResponse
import com.example.alittlemusic.data.logic.model.Song

class RecentPlayActivity : BaseActivity() {

    private lateinit var binding: ActivityRecentPlayBinding
    private val viewModel by lazy { ViewModelProvider(this).get(RecentPlayViewModel::class.java) }

    companion object{
        fun actionStart(context: Context){
            val intent = Intent(context, RecentPlayActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            title = getString(R.string.recent_play)
            it.setDisplayHomeAsUpEnabled(true)
        }


        viewModel.playListLiveData.observe(this, Observer { result ->
            Log.d("test","观察改变"+result.toString())
            val data = result.getOrNull()
            if (data?.list != null){
                binding.recentListCount.text ="（${ data.total}）"
    Log.d("test",data.list.toString())
                viewModel.playList.clear()
                val list = data.list.map { it ->
                    it.data
                }
                viewModel.playList.addAll(list)//model 这里还得调整，但是最近播放为空，看不到数据类型
                showPlayList()
            }else{
                hideLoading()
            }
        })


        binding.recentPlayList.linear().divider(R.drawable.divider_horizontal).setup {
            addType<RecentPlayResponse.DataX> { R.layout.playlist_fragment }
            onBind {
                val data = getModel<RecentPlayResponse.DataX>()
                findView<TextView>(R.id.song_title).text = data.name

                val desc = data.ar.map {
                    it.name
                }
                val descS = desc.joinToString(separator="/")
                findView<TextView>(R.id.song_des).text = "${descS} - ${data.al.name}"
                findView<TextView>(R.id.song_position).text = "${position+1}"
            }
        }.models = viewModel.playList
    }

    private fun showPlayList() {
        binding.recentPlayList.visibility = View.VISIBLE
        binding.recentPlistLoading.visibility = View.GONE
    }
    private fun hideLoading() {
        binding.recentPlayListText.visibility = View.VISIBLE
        binding.recentPlistLoading.visibility = View.GONE
    }
}