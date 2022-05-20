package com.example.alittlemusic.ui.search

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drake.brv.utils.*
import com.example.alittlemusic.R
import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.data.Adapter.PlayListAdapter
import com.example.alittlemusic.data.Adapter.PlayListInfo
import com.example.alittlemusic.data.Adapter.SearchAdapter
import com.example.alittlemusic.data.logic.model.AllResult
import com.example.alittlemusic.data.logic.model.PlayListResult
import com.example.alittlemusic.data.logic.model.Song
import com.example.alittlemusic.databinding.SearchFragmentBinding
import com.example.alittlemusic.ui.playlist.PlayListActivity
import com.example.alittlemusic.util.toast
import com.makeramen.roundedimageview.RoundedImageView

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val viewModel :SearchViewModel by activityViewModels()

    private val binding get() = _binding!!

    companion object {
        fun newInstance(i: Int) : SearchFragment{
            val args = Bundle().apply {
                putInt("fId",i)
            }
            val fragment  = SearchFragment()
            fragment .arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        根据参数决定加载的页面内容
        val bundle: Bundle? = arguments
        val type = bundle?.getInt("fId").toString()
        when(type){
            "0" -> {
                initAll()
            }
            "1" -> initSongsView()
            "2" -> initPlayListView()

        }

    }

    private fun initAll() {
        viewModel.resultLiveData.observe(viewLifecycleOwner , Observer { result ->
            val data = result.getOrNull()
            if (data != null){
//                Log.d("test","ALL搜索歌单不为空"+ data.playList.moreText)
                viewModel.playListResult.clear()
                viewModel.playListResult.addAll(data.playList.playLists)
                binding.searchAllListBtn.text = data.playList.moreText
//                Log.d("test","ALL搜索歌曲不为空"+ data.song.moreText)
                viewModel.songResult.clear()
                viewModel.songResult.addAll(data.song.songs)
                binding.searchAllSongsBtn.text = data.song.moreText
                hideLoading()
            }
        })

        val listMoreText = ""
        binding.searchAllSongs.apply {
            this.linear().divider(R.drawable.divider_horizontal).setup {
                addType<AllResult.SongX> { R.layout.songlsit_item }
                onBind {
                    val data = getModel<AllResult.SongX>()
                    findView<TextView>(R.id.song_title).text = data.name
                    val desc = data.ar.map {
                        it.name
                    }
                    val descS = desc.joinToString(separator="/")
                    findView<TextView>(R.id.song_des).text = "${descS} - ${data.al.name}"
                }
            }.models = viewModel.songResult
            visibility = View.VISIBLE
        }

        binding.searchAllList.apply {
            this.linear().divider(R.drawable.divider_horizontal).setup {
                addType<AllResult.PlayLists> { R.layout.playlist_item }
                onBind {
                    val data = getModel<AllResult.PlayLists>()
                    findView<TextView>(R.id.play_list_title).text = data.name
                    val desc= "${data.trackCount}首，by${data.creator.nickname}，播放${data.playCount}"
                    findView<TextView>(R.id.play_list_count).text = desc
                    val img = findView<RoundedImageView>(R.id.play_list_img)
                    Glide.with(this@SearchFragment).load(data.coverImgUrl).dontAnimate().into(img)
                }
                R.id.playlist_item.onClick {
                    toast("跳转歌单页面")
                    val data = this.getModel<AllResult.PlayLists>()
                    PlayListActivity.actionStart(MyApplication.context,"RPlayList",data.id,
                        PlayListInfo(data.id,data.coverImgUrl,data.name,data.trackCount)
                    )
                }
            }.models = viewModel.playListResult
            visibility = View.VISIBLE
        }

        binding.searchAllListCard.visibility =View.VISIBLE
        binding.searchAllSongsCard.visibility =View.VISIBLE
    }

    private fun initSongsView() {
        val layoutManager = LinearLayoutManager(MyApplication.context)
        val adapter = SearchAdapter(viewModel.songs)
        binding.searchResultSongs.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }

        viewModel.songsLiveData.observe(viewLifecycleOwner , Observer { result ->
            val data = result.getOrNull()
            if (data != null){
                Log.d("test","单曲搜索不为空"+ data.songCount.toString())
                viewModel.songs.clear()
                viewModel.songs.addAll(data.songs)
                adapter.notifyDataSetChanged()
                hideLoading()

            }
        })
    }

    private fun initPlayListView() {
        binding.searchResultList.apply {
            this.linear().divider(R.drawable.divider_horizontal).setup {
                addType<PlayListResult.Playlists> { R.layout.playlist_item }
                onBind {
                    val data = getModel<PlayListResult.Playlists>()
                    findView<TextView>(R.id.play_list_title).text = data.name
                    val desc= "${data.trackCount}首，by${data.creator.nickname}，播放${data.playCount}"
                    findView<TextView>(R.id.play_list_count).text = desc
                    val img = findView<RoundedImageView>(R.id.play_list_img)
                    Glide.with(this@SearchFragment).load(data.coverImgUrl).dontAnimate().into(img)
                }
                R.id.playlist_item.onClick {
                    toast("跳转歌单页面")
                    val data = this.getModel<PlayListResult.Playlists>()
                    PlayListActivity.actionStart(MyApplication.context,"RPlayList",data.id,
                        PlayListInfo(data.id,data.coverImgUrl,data.name,data.trackCount)
                    )
                }
            }.models = viewModel.playList
            visibility = View.VISIBLE
        }
        viewModel.playListLiveData.observe(viewLifecycleOwner , Observer { result ->
            val data = result.getOrNull()
            if (data != null){
                Log.d("test","歌单搜索不为空"+ data.playlistCount)
                viewModel.playList.clear()
                viewModel.playList.addAll(data.playlists)
                hideLoading()
//                binding.searchResultList.visibility = View.VISIBLE
            }
        })

    }

    private fun hideLoading() {
        activity?.findViewById<RelativeLayout>(R.id.search_after).let {
            it?.visibility = View.VISIBLE
        }

        binding.searchResultSongs.visibility = View.VISIBLE

        activity?.findViewById<ProgressBar>(R.id.search_loading).let {
            it?.visibility = View.GONE
        }
    }

}