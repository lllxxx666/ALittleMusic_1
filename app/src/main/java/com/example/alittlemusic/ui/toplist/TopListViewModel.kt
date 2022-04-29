package com.example.alittlemusic.ui.toplist

import androidx.lifecycle.*
import com.example.alittlemusic.data.logic.model.TopListDetailResponse
import com.example.alittlemusic.data.logic.network.Repository.PlayListRepository

class TopListViewModel (): ViewModel() {

    private val refreshLiveData = MutableLiveData<Any?>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

//    private var _toplist = MutableLiveData<List<TopListDetailResponse.TopList>>()
//    var toplist : LiveData<List<TopListDetailResponse.TopList>> =   _toplist
    //    排行榜
    val topList = ArrayList<TopListDetailResponse.TopList>()
    val topListLiveData = Transformations.switchMap(refreshLiveData){
        PlayListRepository.getTopListDetail()
    }
    init {
        refreshLiveData.value = refreshLiveData.value
    }
}