package com.example.alittlemusic.data.logic.network.service

import com.example.alittlemusic.data.logic.model.SearchPlayListResponse
import com.example.alittlemusic.data.logic.model.SearchResponse
import com.example.alittlemusic.data.logic.model.SearchSongsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author liuxin
 * Created on 2022/4/21
 * @description：---搜索，可选参数如下
 * limit : 返回数量 , 默认为 30
 * offset : 偏移数量，用于分页 , 如 : 如 :( 页数 -1)*30, 其中 30 为 limit 的值 , 默认为 0
 * type: 搜索类型；默认为 1 即单曲 , 取值意义 : 1: 单曲, 10: 专辑, 100: 歌手, 1000: 歌单, 1002: 用户,
 *      1004: MV, 1006: 歌词, 1009: 电台, 1014: 视频, 1018:综合, 2000:声音(搜索声音返回字段格式会不一样)
 */
interface SearchService {

    @GET("search?type=1018")    //综合搜索
    fun search(@Query("keywords") keywords: String): Call<SearchResponse>

    @GET("search")    //单曲搜索
    fun searchSongs(@Query("keywords") keywords: String): Call<SearchSongsResponse>

    @GET("search?type=1000")    //歌单搜索
    fun searchPlayList(@Query("keywords") keywords: String): Call<SearchPlayListResponse>

}