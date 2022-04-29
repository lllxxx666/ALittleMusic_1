package com.example.alittlemusic.ui.listSquare

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.drake.brv.utils.grid
import com.drake.brv.utils.setup
import com.example.activitytest.BaseActivity
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.AllResult
import com.example.alittlemusic.data.logic.model.BoutiquePlaylistResponse
import com.example.alittlemusic.databinding.ActivityListSquareBinding
import com.makeramen.roundedimageview.RoundedImageView

class ListSquareActivity : BaseActivity() {
    companion object{
        fun actionStart(context: Context){
            val intent = Intent(context, ListSquareActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }
    }

    private lateinit var binding: ActivityListSquareBinding
    private val viewModel by lazy { ViewModelProvider(this).get(ListSquareViewModel::class.java) }


    private val tabs = arrayOf( //看时间再决定要不要扩展
        "官方",
        "精品",
        "歌单"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSquareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.listSquareToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.listSquareToolbar.setNavigationOnClickListener {
            finish()
        }

        initBoutiquePlaylist()

    }

    private fun initBoutiquePlaylist() {
        viewModel.BPlayListLiveData.observe(this, Observer { result ->
            val data = result.getOrNull()
            if (data != null){
                viewModel.BPlayList.clear()
                viewModel.BPlayList.addAll(data.playlists)
                hideLoading()
            }

        })
        binding.boutiquePlaylist.apply {
            this.grid(3).setup {
                addType<BoutiquePlaylistResponse.Playlists> { R.layout.rplaylist_item }
                onBind {
                    val data = getModel<BoutiquePlaylistResponse.Playlists>()
                    findView<TextView>(R.id.rpaylist_title).text = data.name
                    val img = findView<RoundedImageView>(R.id.rpaylist_img)
                    Glide.with(this@ListSquareActivity).load(data.coverImgUrl).dontAnimate().into(img)
                }
            }.models = viewModel.BPlayList

        }
    }

    private fun hideLoading() {
        binding.listSquareLoading.visibility = View.GONE
        binding.boutiquePlaylist.visibility = View.VISIBLE
    }
}