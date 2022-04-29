package com.example.alittlemusic.data.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.dao.Banner

/**
 *
 * @author liuxin
 * Created on 2022/3/28
 * @description：---
 */
class BannerPagerAdapter(var list: ArrayList<Banner>) : RecyclerView.Adapter<BannerPagerAdapter.BannerViewHolder>() {
    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img : ImageView = view.findViewById(R.id.banner_img)
        fun bind(model:Banner){
//            img.setImageURI(Uri.parse(model.imageUrl))
            Glide.with(img).load(model.imageUrl).into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.banner_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}