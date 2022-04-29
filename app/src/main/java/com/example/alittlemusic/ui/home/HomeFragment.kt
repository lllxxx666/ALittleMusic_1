package com.example.alittlemusic.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.example.alittlemusic.R
import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.databinding.FragmentHomeBinding
import com.example.alittlemusic.data.Adapter.BannerPagerAdapter
import com.example.alittlemusic.data.Adapter.PlayListInfo
import com.example.alittlemusic.data.Adapter.RPlayListAdapter
import com.example.alittlemusic.data.logic.model.DayRecommendResponse
import com.example.alittlemusic.ui.listSquare.ListSquareActivity
import com.example.alittlemusic.ui.playlist.PlayListActivity
import com.example.alittlemusic.util.toast
import com.makeramen.roundedimageview.RoundedImageView
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private lateinit var rPlaylistAdapter: RPlayListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        homeViewModel.refresh()
        val wormDotsIndicator : WormDotsIndicator = binding.wormDotsIndicator
        val bannerPager : ViewPager2 = binding.homeBanner
        val adapter = BannerPagerAdapter(homeViewModel.bannerList)
        bannerPager.adapter = adapter
        wormDotsIndicator.setViewPager2(bannerPager)


        homeViewModel.bannerLiveData.observe(viewLifecycleOwner, Observer { result ->
            val list = result.getOrNull()
            if (list != null){
                homeViewModel.bannerList.clear()
                homeViewModel.bannerList.addAll(list)
                adapter.notifyDataSetChanged()
                showBanner()
            }
        })

        val layoutManager = GridLayoutManager(activity,3)
        layoutManager.canScrollHorizontally()
        binding.homeRPlaylist.layoutManager = layoutManager
        val rPlaylistAdapter = RPlayListAdapter(this,homeViewModel.rPlayList)
        binding.homeRPlaylist.adapter = rPlaylistAdapter

//        推荐歌单
        homeViewModel.rPlayListLiveData.observe(viewLifecycleOwner, Observer { result ->
//            Log.d("test","我得到了推荐歌单"+result.toString())
            val list = result.getOrNull()
            if (list != null){
                homeViewModel.rPlayList.clear()
                homeViewModel.rPlayList.addAll(list)
//                rPlaylistAdapter.notifyDataSetChanged()
                showRPlayList()
            }
        })


        //        每日推荐歌单
        initDailyPlayList()


//        homeViewModel.loadingIsV.observe(viewLifecycleOwner, Observer { t ->
//            hideLoading(t)
//        })

        //        跳转操作
        binding.homeMenuSong.let {
            it.setOnClickListener {
                PlayListActivity.actionStart(MyApplication.context,"dailySongs",0, PlayListInfo(null,null,null,null))
            }
        }
        binding.homeMenuPlaylist.let {
            it.setOnClickListener {
                ListSquareActivity.actionStart(MyApplication.context)
            }
        }
        binding.homeMenuToplist.let {
            it.setOnClickListener {

            }
        }
        binding.homeMenu4.let {
            it.setOnClickListener {
                toast("正在施工中，待开发")
            }
        }
        binding.rplaylistBtn.let {
            it.setOnClickListener {
                ListSquareActivity.actionStart(MyApplication.context)
            }
        }
        return root
    }
//      每日推荐歌单
    private fun initDailyPlayList() {
        homeViewModel.dailyPlayListLiveData.observe(viewLifecycleOwner, Observer { result ->
            Log.d("test","我得到了每日推荐歌单"+result.toString())
            val list = result.getOrNull()
            if (list != null){
                homeViewModel.dailyPlayList.clear()
                homeViewModel.dailyPlayList.addAll(list)

            }
        })
        binding.dailyRplaylist.apply {
            this.linear(RecyclerView.HORIZONTAL).setup {
                addType<DayRecommendResponse.Recommend> { R.layout.rplaylist_item }
                onBind {
                    val data = getModel<DayRecommendResponse.Recommend>()
                    if (data.creator.userType != 10) {
                        findView<TextView>(R.id.rpaylist_title).text = data.name
                        val img = findView<RoundedImageView>(R.id.rpaylist_img)
                        Glide.with(this@HomeFragment).load(data.picUrl).dontAnimate().into(img)
                    }
                }
            }.models = homeViewModel.dailyPlayList

            visibility = View.VISIBLE
        }
    }

    private fun showBanner() {
        binding.homeBanner.visibility = View.VISIBLE
        homeViewModel.bannerIsV = true
    }

    private fun showRPlayList() {
        binding.homeRPlaylist.visibility = View.VISIBLE
        homeViewModel.rpListIsV = true
//后面要改
        binding.homeLoading.visibility = View.GONE
    }

    private fun hideLoading(t: Boolean) {

        Log.d("test","隐藏加载"+t.toString())
        if (t)  binding.homeLoading.visibility = View.GONE
        else binding.homeLoading.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}