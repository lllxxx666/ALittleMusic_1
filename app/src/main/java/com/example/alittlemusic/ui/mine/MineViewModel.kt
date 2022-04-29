package com.example.alittlemusic.ui.mine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.alittlemusic.data.Adapter.PlayListInfo
import com.example.alittlemusic.data.logic.model.UserPlayListResponse
import com.example.alittlemusic.data.logic.network.Repository.PlayListRepository

class MineViewModel : ViewModel() {

    lateinit var playListInfo: PlayListInfo
    private val refreshLiveData = MutableLiveData<Any?>()

    lateinit var myLove: UserPlayListResponse.Playlist
    val playList = ArrayList<UserPlayListResponse.Playlist>()
    val uid = 1482073429
    val playListLiveData = Transformations.switchMap(refreshLiveData){
        PlayListRepository.getUserPlayList(this.uid)
    }

    init {
        refreshLiveData.value = refreshLiveData.value
    }

}