package com.example.alittlemusic.data.Adapter

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.TopListDetailResponse
import com.example.alittlemusic.ui.playlist.PlayListActivity
import com.makeramen.roundedimageview.RoundedImageView
import java.io.Serializable


/**
 *
 * @author liuxin
 * Created on 2022/4/6
 * @description：---
 */
class TopListAdapter (private val fragment: Fragment, private val list: List<TopListDetailResponse.TopList>)
    : RecyclerView.Adapter<TopListAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toplist_card: View = view.findViewById(R.id.toplist_card)
        val title: TextView = view.findViewById(R.id.toplist_title)
        val img: RoundedImageView = view.findViewById(R.id.toplist_img)
        val updateFrequency: TextView = view.findViewById(R.id.updateFrequency)
        val card: CardView = view.findViewById(R.id.toplist_card)
        val tracks: RecyclerView = view.findViewById(R.id.tracks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.official_rankings_item, parent, false)
        val holder = ViewHolder(view)
        holder.toplist_card.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val item = list[position]
            PlayListActivity.actionStart(it.context,"TopList",item.id,PlayListInfo(item.id,item.coverImgUrl,item.name,item.trackCount))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.name
        holder.updateFrequency.text = item.updateFrequency
        holder.title.text = item.name
        Glide.with(holder.itemView).load(item.coverImgUrl).into(holder.img)
        if (position % 2 ==1) holder.card.setBackgroundColor(Color.parseColor("#E0FFFF"))
        else holder.card.setBackgroundColor(Color.parseColor("#FFE4E1"))

        holder.tracks.layoutManager = LinearLayoutManager(Activity())
        holder.tracks.adapter = ListAdapter(item.tracks)
    }

    override fun getItemCount(): Int = list.size

    inner class ListAdapter(private val tracks: List<TopListDetailResponse.Track>)
        : RecyclerView.Adapter<ListAdapter.ViewHolder>(){
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val part1: TextView = view.findViewById(R.id.toplist_item_part1)
            val part2: TextView = view.findViewById(R.id.toplist_item_part2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.toplist_item, parent, false)
            val holder = ViewHolder(view)
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = tracks[position]
            holder.part1.text = "${position+1}.${item.first}"
            holder.part2.text = " - ${item.second}"
        }

        override fun getItemCount(): Int = tracks.size
    }
    inner class PlayerInfo(var playList: List<TopListDetailResponse.TopList>, var position : Int ): Serializable
}
