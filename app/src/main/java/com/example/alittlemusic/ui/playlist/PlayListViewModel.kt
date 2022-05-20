package com.example.alittlemusic.ui.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.data.logic.model.DayRecommendSongResponse
import com.example.alittlemusic.data.logic.model.Song
import com.example.alittlemusic.data.logic.network.Repository.HomeRepository
import com.example.alittlemusic.data.logic.network.Repository.PlayListRepository
import kotlinx.coroutines.launch

class PlayListViewModel: ViewModel() {

    private val refreshLiveData = MutableLiveData<Any?>()
    private val searchId = MutableLiveData<Number>()
    var playList = ArrayList<Song>()
    val playListLiveData = Transformations.switchMap(searchId){ it ->
        PlayListRepository.getPlayListAll(it)
    }
    fun getPlayList(){
        searchId.value = PlayListActivity.listId
    }

    //  每日推荐歌曲
    private val dayLiveData = MutableLiveData<Any?>()
    var dailySong = ArrayList<Song>()
    val dailySongLiveData = Transformations.switchMap(dayLiveData){
        HomeRepository.getDailySong()
    }
    fun getDailySong(){
        searchId.value = PlayListActivity.listId
    }


    private val hasCollect = MutableLiveData<Boolean>()     //该歌单页面是否有收藏功能
    val collectListLiveData = Transformations.switchMap(hasCollect){ it ->
        PlayListRepository.getUserPlayList(MyApplication.uid)
    }
    fun getCollectList() {
        hasCollect.value = true
    }
    private val collectType = MutableLiveData<Int>()
    fun collect(i: Int) {
        collectType.value = i
    }


    val collectLiveData = Transformations.switchMap(collectType){ it ->
        PlayListRepository.collect(collectType.value!!,it)
    }
//    init {
//        viewModelScope.launch {
//            searchId.value = PlayListActivity.listId
//        }
//    }
}