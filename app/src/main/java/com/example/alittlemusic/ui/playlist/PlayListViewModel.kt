package com.example.alittlemusic.ui.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alittlemusic.data.logic.model.Song
import com.example.alittlemusic.data.logic.network.Repository.PlayListRepository
import kotlinx.coroutines.launch

class PlayListViewModel: ViewModel() {

    private val searchId = MutableLiveData<Number>()
    var playList = ArrayList<Song>()

    val playListLiveData = Transformations.switchMap(searchId){ it ->
        PlayListRepository.getPlayListAll(it)
    }

    private val collectType = MutableLiveData<Int>()
    fun collect(i: Int) {
        collectType.value = i
    }

    val collectLiveData = Transformations.switchMap(collectType){ it ->
        PlayListRepository.collect(collectType.value!!,it)
    }
    init {
        viewModelScope.launch {
            searchId.value = PlayListActivity.listId
        }
    }
}