package com.example.alittlemusic.data.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.CommonModel

/**
 *
 * @author liuxin
 * Created on 2022/3/29
 * @description：---
 */
class CommonAdapter (private val fragment: Fragment, private val list: List<CommonModel>)
    : RecyclerView.Adapter<CommonAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.common_title)
        val img: ImageView = view.findViewById(R.id.common_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.img.setImageResource(item.img)
    }

    override fun getItemCount(): Int = list.size
}