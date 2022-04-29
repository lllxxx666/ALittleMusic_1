package com.example.alittlemusic.data.logic.network.Repository

import android.util.Log
import androidx.lifecycle.liveData
import com.example.alittlemusic.data.logic.model.TopListDetailResponse
import com.example.alittlemusic.data.logic.network.mNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 *
 * @author liuxin
 * Created on 2022/4/11
 * @description：---
 */
object PlayListRepository {
    fun getPlayListAll(id : Number) = fire(Dispatchers.IO){
        val response = mNetwork.getPlayListAll(id)
        if (response.code == 200) {
            val result = response.songs
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${response.code}"))
        }
    }
    fun getTopListDetail() = fire(Dispatchers.IO) {
        val toplistResponse = mNetwork.getTopListDetail()
        if (toplistResponse.code == 200) {
            val result = ArrayList<TopListDetailResponse.TopList>(toplistResponse.toplist.filter { !it.tracks.isEmpty() })
//            result = result.filter { it.tracks[0]!=null } as ArrayList<TopListDetailResponse.TopList>
//            Log.d("test","仓库层，排行榜"+result.toString())
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${toplistResponse.code}"))
        }
    }
//    获得用户歌单 -》 我喜欢的音乐，收藏的歌单
    fun getUserPlayList(uid: Int) = fire(Dispatchers.IO) {
        val timestamp= System.currentTimeMillis()
        val response = mNetwork.getUserPlayList(uid,timestamp)
        if (response.code == 200) {
            val result = response.playlist
//            Log.d("test","仓库层，获得用户歌单"+result.toString())
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${response.code}"))
        }
    }
//    获得最近播放-歌单
    fun getRecentPlayList() = fire(Dispatchers.IO) {
        val timestamp= System.currentTimeMillis()
        val response = mNetwork.getRecentPlayList(timestamp)
        if (response.code == 200) {
            val result = response.data
    //            Log.d("test","仓库层，获得最近播放-歌单"+result.toString())
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${response.code}"))
        }
    }
    //    获得精品歌单
    fun getBPlayList(tags: String?) = fire(Dispatchers.IO) {
        val timestamp= System.currentTimeMillis()
        val response = mNetwork.getBPlayList(tags)
        if (response.code == 200) {
            val result = response
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${response.code}"))
        }
    }
//收藏歌单
    fun collect(t: Int, id: Int) = fire(Dispatchers.IO) {
        val response = mNetwork.subPlayList(t,id)
        if (response.code == 200) {
            when(t){
                1 -> response.message = "收藏成功"
                2 -> response.message = "取消收藏成功"
            }

            Result.success(response)
        } else {
            Result.failure(RuntimeException("response status is ${response.code}"))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }


}