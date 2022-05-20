package com.example.alittlemusic.ui.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alittlemusic.data.logic.model.Song
import com.example.alittlemusic.data.logic.network.Repository.PlayListRepository
import com.example.alittlemusic.ui.playlist.PlayListActivity
import kotlinx.coroutines.launch

class PlyerViewModel : ViewModel() {
    private val songId = MutableLiveData<Number>()
    var playList = ArrayList<Song>()

    val playListLiveData = Transformations.switchMap(songId){ it ->
        PlayListRepository.getPlayListAll(it)
    }

//    val songUrl = MutableLiveData<String>()
//    val songUrlLiveData = Transformations.switchMap(songId){  it ->
//
//    }

    init {
//        viewModelScope.launch {
//            searchId.value = PlayListActivity.listId
//        }
    }
}