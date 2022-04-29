package com.example.alittlemusic.data.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.RPlayListResponse
import com.example.alittlemusic.ui.playlist.PlayListActivity
import java.io.Serializable

/**
 *
 * @author liuxin
 * Created on 2022/3/29
 * @description：---
 */
class RPlayListAdapter (private val fragment: Fragment, private val list: List<RPlayListResponse.Result>)
    : RecyclerView.Adapter<RPlayListAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: Long = 0
        val title: TextView = view.findViewById(R.id.rpaylist_title)
        val img: ImageView = view.findViewById(R.id.rpaylist_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rplaylist_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val item = list[position]
            PlayListActivity.actionStart(it.context,"RPlayList",item.id,PlayListInfo(item.id,item.picUrl,item.name,item.trackCount))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.name
        holder.id = item.id
        Glide.with(holder.itemView).load(item.picUrl).into(holder.img)
    }

    override fun getItemCount(): Int = list.size
}
class PlayListInfo(var id: Long?, var img: String?, var title: String?, var trackCount: Int?): Serializable