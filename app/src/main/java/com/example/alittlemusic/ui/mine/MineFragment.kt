package com.example.alittlemusic.ui.mine

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.alittlemusic.R
import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.databinding.FragmentMineBinding
import com.example.alittlemusic.data.Adapter.CommonAdapter
import com.example.alittlemusic.data.Adapter.MPlayListAdapter
import com.example.alittlemusic.data.Adapter.PlayListInfo
import com.example.alittlemusic.data.logic.model.CommonModel
import com.example.alittlemusic.ui.login.LoginActivity
import com.example.alittlemusic.ui.playlist.PlayListActivity
import com.example.alittlemusic.ui.recentPlay.RecentPlayActivity

class MineFragment : Fragment() {

    private var _binding: FragmentMineBinding? = null
    private val viewModel by lazy { ViewModelProvider(this).get(MineViewModel::class.java) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val menuList = ArrayList<CommonModel>()
    private val playlistList = ArrayList<CommonModel>()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMineBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initMenuData()
        val layoutManager = LinearLayoutManager(activity)
        binding.mineMenu.layoutManager = layoutManager
        val adapter = CommonAdapter(this,menuList)
        binding.mineMenu.adapter = adapter

        //        歌单
        val playlistLayoutManager = LinearLayoutManager(activity)
        binding.minePlaylist.layoutManager = playlistLayoutManager
        val playListAdapter = MPlayListAdapter(viewModel.playList)
        binding.minePlaylist.adapter = playListAdapter

//        获取用户歌单
        viewModel.playListLiveData.observe(viewLifecycleOwner, Observer { result ->
            val data = result.getOrNull()
            if (data != null){
                val myLove= data.filter { it.creator.userId == viewModel.uid}[0]
                viewModel.myLove = myLove
                viewModel.playListInfo = PlayListInfo(myLove.id,myLove.coverImgUrl,"我喜欢的音乐",myLove.trackCount)
                val subPlaylistLove= data.filter { it.creator.userId != viewModel.uid }
//              我喜欢的音乐
                Glide.with(this).load(myLove.coverImgUrl).into(binding.loveImg)
                binding.mloveCount.text = "一共${myLove.trackCount}首"
                binding.menuMloveNum.text ="${myLove.trackCount}"
//                收藏歌单
                viewModel.playList.clear()
                viewModel.playList.addAll(subPlaylistLove)
                showPlayList()
                MyApplication.CollectedList = subPlaylistLove.map { it.id} as ArrayList
            }
        })


//        login_entrance
        val loginEntrance = binding.loginEntrance
        loginEntrance.setOnClickListener {
            LoginActivity.actionStart(MyApplication.context)
        }


//        跳转操作
        binding.menuRecentplay.let {
            it.setOnClickListener {
                RecentPlayActivity.actionStart(MyApplication.context)
            }
        }
        binding.menuMlove.let {
            it.setOnClickListener {
                val data = viewModel.myLove
                viewModel.playListInfo.id?.let { it1 ->
                    PlayListActivity.actionStart(MyApplication.context,"RPlayList",
                        it1,viewModel.playListInfo)
                }
            }
        }
        binding.mineLoveSongs.let {
            it.setOnClickListener {
                val data = viewModel.myLove
                viewModel.playListInfo.id?.let { it1 ->
                    PlayListActivity.actionStart(MyApplication.context,"RPlayList",
                        it1,viewModel.playListInfo)
                }
            }
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.getSharedPreferences("data", Context.MODE_PRIVATE).apply {
            if (this != null){
                val userinfo = getStringSet("user_info",null)
                Log.d("test", "用户信息"+userinfo)
            }

       }
    }

    private fun showPlayList() {
        binding.minePlaylist.visibility = View.VISIBLE
        binding.mineLoading.visibility = View.GONE
    }


    private fun initMenuData() {
        menuList.add(CommonModel("设置", R.drawable.ic_setup))
        menuList.add(CommonModel("帮助与客服", R.drawable.ic_question))
        menuList.add(CommonModel("问题与建议", R.drawable.ic_suggest))
        menuList.add(CommonModel("检查更新", R.drawable.ic_updata))
        menuList.add(CommonModel("关于", R.drawable.ic_prompt))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}