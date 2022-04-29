package com.example.alittlemusic.data.logic.network.service

import com.example.alittlemusic.data.logic.model.DayRecommendSongResponse
import com.example.alittlemusic.data.logic.model.RecentPlayResponse
import com.example.alittlemusic.data.logic.model.RecentPlistResponse
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * @author liuxin
 * Created on 2022/4/10
 * @description：---
 */
interface SongService {

    @GET("song/url")
    fun getSongUrl(@Query("id") id: Int): Call<Any>

    @GET("song/detail")
    fun getSongDetail(@Query("ids") ids: Int): Call<Any>

    @GET("check/music")     //是否可用
    fun checkSong(@Query("id") id: Int): Call<Any>

    @GET("lyric")   //歌词
    fun getLyric(@Query("id") id: Int): Call<Any>

    @GET("like")    //喜欢音乐 like 默认ture,ture 为喜欢，false 为不喜欢
    fun likeSong(@Query("id") id: Int,@Query("like") like: Boolean):  Call<Any>

    @GET("playlist/tracks")    //对歌单添加或删除歌曲 op: 从歌单增加单曲为 add, 删除为 del   pid: 歌单 id  tracks: 歌曲 id,可多个,用逗号隔开
    fun subSong(@Query("op") op: String,@Query("pid") pid: Int,@Query("tracks") tracks: Int):  Call<Any>

    @Headers("User-Agent:7d384af7d1b0908e7b6f25de5142a9c54ba2a162576a5131016b270f7c497bdc993166e004087dd388801f7711c82f1bd07dd68cd3eabc7746b14e3f0c3f8af9fe5c85647582a507")
    @FormUrlEncoded
    @POST("record/recent/song")      //最近播放-歌单
    fun getRecentSongs(@Field("timestamp") timestamp:Long): Call<RecentPlayResponse>

    @FormUrlEncoded
    @POST("recommend/songs")
    fun getDailySong(@Field("timestamp") timestamp :Long): Call<DayRecommendSongResponse>
}