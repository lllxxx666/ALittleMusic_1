package com.example.alittlemusic.data.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.UserPlayListResponse
import com.example.alittlemusic.ui.playlist.PlayListActivity

/**
 *
 * @author liuxin
 * Created on 2022/4/18
 * @description：---
 */
class MPlayListAdapter(private val list: List<UserPlayListResponse.Playlist>)
    : RecyclerView.Adapter<MPlayListAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        var id: Int = 0
        val title : TextView = view.findViewById(R.id.play_list_title)
        val count : TextView = view.findViewById(R.id.play_list_count)
        val img : ImageView = view.findViewById(R.id.play_list_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.playlist_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val item = list[position]
            PlayListActivity.actionStart(it.context,"RPlayList",item.id,PlayListInfo(item.id,item.coverImgUrl,item.name,item.trackCount))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.name
        holder.count.text = "${item.trackCount}首,by${item.creator.nickname}"
        Glide.with(holder.img).load(item.coverImgUrl).dontAnimate().into(holder.img)

    }

    override fun getItemCount(): Int  = list.size
}