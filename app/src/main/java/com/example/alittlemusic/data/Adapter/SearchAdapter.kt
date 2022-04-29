package com.example.alittlemusic.data.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.SongsResult
import com.example.alittlemusic.ui.player.PlayerActivity
import java.io.Serializable

/**
 *
 * @author liuxin
 * Created on 2022/4/21
 * @description：---
 */
class SearchAdapter(private val list: List<SongsResult.Song>)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: TextView = view.findViewById<TextView?>(R.id.song_position).apply {
            this.visibility = View.GONE
        }
        var position: TextView = view.findViewById(R.id.song_position)
        val title: TextView = view.findViewById(R.id.song_title)
        val desc: TextView = view.findViewById(R.id.song_des)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.playlist_fragment, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
//            这时候是要去播放音乐了
//            PlayerActivity.actionStart(it.context,PlayerInfo(list, 0))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = list[position]
        holder.title.text = song.name
        val desc = song.artists.map {
            it.name
        }
        val descS = desc.joinToString(separator="/")

        holder.desc.text = "${descS} - ${song.album.name}"
    }

    override fun getItemCount(): Int = list.size
//    class PlayerInfo(var playList: List<Result.Song>, var position: Int ): Serializable
}
