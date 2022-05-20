package com.example.alittlemusic.data.logic.network.service

import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.data.logic.model.*
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * @author liuxin
 * Created on 2022/3/29
 * @description：---
 */
interface PlayListService {

    @GET("personalized")        //获取推荐歌单
    fun getRPlayList(@Query("limit") limit : Int): Call<RPlayListResponse>

    @GET("toplist/detail")
    fun getTopListDetail(): Call<TopListDetailResponse>

    @GET("playlist/track/all")
    fun getPlayListAll(@Query("id") id: Number): Call<PlayListResponse>

    @FormUrlEncoded    //收藏歌单 //t : 类型,1:收藏,2:取消收藏 id : 歌单 id
    @POST("playlist/subscribe?cookie${MyApplication.fakeCookie}")
    fun subPlayList(@Field("t")t: Int,@Field("id") id: Int): Call<Response>

    @GET("related/playlist")
    fun getRelatedPlayList(@Query("id")id: Number): Call<Any>

    @FormUrlEncoded
    //@POST("recommend/resource")      //获取每日推荐歌单
    @POST("recommend/resource?cookie=MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/clientlog;;MUSIC_U=7d384af7d1b0908e7b6f25de5142a9c59bf2ac5bc68de572a685c2dc524064cc993166e004087dd34dfc7c0121b219268371295cf35bfce2f83a9317205494d650e4c3e6b3c397247a561ba977ae766d; Max-Age=1296000; Expires=Fri, 06 May 2022 22:54:08 GMT; Path=/;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/clientlog;;__remember_me=true; Max-Age=1296000; Expires=Fri, 06 May 2022 22:54:08 GMT; Path=/;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/feedback;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/feedback;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/feedback;;MUSIC_SNS=; Max-Age=0; Expires=Thu, 21 Apr 2022 22:54:08 GMT; Path=/;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/feedback;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/feedback;;__csrf=8703f63673a4cd6a7d98a21e22857f2f; Max-Age=1296010; Expires=Fri, 06 May 2022 22:54:18 GMT; Path=/;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/clientlog;")      //获取每日推荐歌单
    fun getDailyRPlayList(@Field("timecurrentTimeMillis")timecurrentTimeMillis:Long): Call<DayRecommendResponse>

    @FormUrlEncoded
    @POST("user/playlist?cookie=${MyApplication.fakeCookie}")      //获取每日推荐歌单
    fun getUserPlayList(@Field("uid") uid:Int,@Field("timestamp") timestamp:Long): Call<UserPlayListResponse>

    @FormUrlEncoded
    @POST("record/recent/playlist")      //最近播放-歌单
    fun getRecentPlayList(@Field("timestamp") timestamp:Long): Call<RecentPlistResponse>

    @GET("top/playlist/highquality")    //获取精品歌单
    fun getBPlayList(@Query("tags")tags: String?): Call<BoutiquePlaylistResponse>

}