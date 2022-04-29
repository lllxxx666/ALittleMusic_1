package com.example.alittlemusic.ui.recentPlay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.alittlemusic.data.logic.model.RecentPlayResponse
import com.example.alittlemusic.data.logic.model.RecentPlistResponse
import com.example.alittlemusic.data.logic.network.Repository.PlayListRepository
import com.example.alittlemusic.data.logic.network.Repository.Repository
import retrofit2.Response

/**
 *
 * @author liuxin
 * Created on 2022/4/18
 * @description：---
 */
class RecentPlayViewModel:ViewModel() {
    private val refreshLiveData = MutableLiveData<Any?>()


    val playList = ArrayList<RecentPlayResponse.DataX>()
    val playListLiveData = Transformations.switchMap(refreshLiveData){
        Repository.getRecentSongs()
    }
    init {
        refreshLiveData.value = refreshLiveData.value
    }
}