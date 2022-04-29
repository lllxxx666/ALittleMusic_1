package com.example.alittlemusic.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.alittlemusic.data.logic.model.AllResult
import com.example.alittlemusic.data.logic.model.PlayListResult
import com.example.alittlemusic.data.logic.model.SearchResponse
import com.example.alittlemusic.data.logic.model.SongsResult
import com.example.alittlemusic.data.logic.network.Repository.Repository
import com.example.alittlemusic.data.logic.network.Repository.SearchRepository

class SearchViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val songs =ArrayList<SongsResult.Song>()
    val songsLiveData = Transformations.switchMap(searchLiveData) { query ->
        SearchRepository.searchSongs(query)
    }

    val playList =ArrayList<PlayListResult.Playlists>()
    val playListLiveData = Transformations.switchMap(searchLiveData) { query ->
        SearchRepository.searchPlayLists(query)
    }

    val playListResult =ArrayList<AllResult.PlayLists>()
    val songResult =ArrayList<AllResult.SongX>()
    val resultLiveData = Transformations.switchMap(searchLiveData) { query ->
        SearchRepository.search(query)
    }


    fun search(query: String) {
        searchLiveData.value = query
    }

}