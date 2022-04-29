package com.example.alittlemusic.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.alittlemusic.data.logic.dao.Banner
import com.example.alittlemusic.data.logic.model.*
import com.example.alittlemusic.data.logic.network.Repository.HomeRepository
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {
//    private val repository by lazy { HomeRepository.getBanner() }
//    val bannerResult = async { HomeRepository.getBanner() }

    private val refreshLiveData = MutableLiveData<Any?>()


    val bannerList = ArrayList<Banner>()
    val bannerLiveData = Transformations.switchMap(refreshLiveData){
        HomeRepository.getBanner()
    }
//    推荐歌单
    val rPlayList = ArrayList<RPlayListResponse.Result>()
    val rPlayListLiveData = Transformations.switchMap(refreshLiveData){
        HomeRepository.getRPlayList(6)
    }


//  每日推荐歌曲
    val dailySong = ArrayList<DayRecommendSongResponse.DailySong>()
    val dailySongLiveData = Transformations.switchMap(refreshLiveData){
        HomeRepository.getDailySong()
    }

    //  每日推荐歌单
    val dailyPlayList = ArrayList<DayRecommendResponse.Recommend>()
    val dailyPlayListLiveData = Transformations.switchMap(refreshLiveData){
        HomeRepository.getDailyRPlayList()
    }

    fun refresh(){
        refreshLiveData.value = refreshLiveData.value
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var bannerIsV: Boolean = false
    var rpListIsV: Boolean = false

    val _loadingIsV = MutableLiveData<Boolean>().apply {
        value = bannerIsV && rpListIsV
        Log.d("test",value.toString())
    }
    var loadingIsV = _loadingIsV

    init {
        refreshLiveData.value = refreshLiveData.value
    }
}