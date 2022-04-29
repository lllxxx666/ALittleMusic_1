package com.example.alittlemusic.ui.listSquare

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.alittlemusic.data.logic.model.BoutiquePlaylistResponse
import com.example.alittlemusic.data.logic.network.Repository.PlayListRepository

class ListSquareViewModel : ViewModel() {

    private val refreshLiveData = MutableLiveData<Any?>()

    val BPlayList = ArrayList<BoutiquePlaylistResponse.Playlists>()
    val tags: String? =null
    val BPlayListLiveData = Transformations.switchMap(refreshLiveData) {
        PlayListRepository.getBPlayList(tags)
    }

    init {
        refreshLiveData.value = refreshLiveData.value
    }
}