package com.example.alittlemusic.data.logic.network.service

import com.example.alittlemusic.data.logic.dao.BannerResponse
import com.example.alittlemusic.data.logic.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonService {

    @GET("banner")
    fun getBanner(): Call<BannerResponse>

    @GET("search")
    fun search(@Query("keywords") keywords: String): Call<SearchResponse>
}