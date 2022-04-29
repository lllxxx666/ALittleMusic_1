package com.example.alittlemusic.data.Adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alittlemusic.data.logic.model.Song

/**
 *
 * @author liuxin
 * Created on 2022/4/15
 * @description：---
 */
class SearchResultAdapter(private val list: List<Song>): RecyclerView.Adapter<SearchResultAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size


}