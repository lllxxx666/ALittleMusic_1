package com.example.alittlemusic.data.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.Song
import com.example.alittlemusic.ui.player.PlayerActivity
import java.io.Serializable

/**
 *
 * @author liuxin
 * Created on 2022/4/11
 * @description：---
 */
class PlayListAdapter(private val list: ArrayList<Song>)
    : RecyclerView.Adapter<PlayListAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        var position: TextView = view.findViewById(R.id.song_position)
        val title: TextView = view.findViewById(R.id.song_title)
        val desc: TextView = view.findViewById(R.id.song_des)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.playlist_fragment, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
//            这时候是要去播放音乐了
            PlayerActivity.actionStart(it.context,PlayerInfo(list, holder.id))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = list[position]
        holder.title.text = song.name
        val desc = song.ar.map {
            it.name
        }
        val descS = desc.joinToString(separator="/")

        holder.desc.text = "${descS} - ${song.al.name}"

        holder.position.text = "${position + 1}"
//        holder.id = song.id
        holder.id = position

    }

    override fun getItemCount(): Int = list.size

}
class songs(var name: String,var ar:String): Serializable
class PlayerInfo(var playList: List<Song>, var position: Int ): Serializable