package com.example.alittlemusic.data.logic.network.Repository

import android.util.Log
import androidx.lifecycle.liveData
import com.example.alittlemusic.data.logic.dao.Banner
import com.example.alittlemusic.data.logic.network.mNetwork
import com.example.alittlemusic.util.toast
import kotlinx.coroutines.Dispatchers
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

object HomeRepository{

    fun getBanner() = fire(Dispatchers.IO) {
        val bannerResponse = mNetwork.getBanner()
        if (bannerResponse.code == 200) {
            val banners = ArrayList<Banner>(bannerResponse.banners)
            Result.success(banners)
        } else {
            Result.failure(RuntimeException("response status is ${bannerResponse.code}"))
        }
    }
    fun getRPlayList(limit : Int) = fire(Dispatchers.IO) {
        val rplaylistResponse = mNetwork.getRPlayList(limit)
        if (rplaylistResponse.code == 200) {
            val result = rplaylistResponse.result
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${rplaylistResponse.code}"))
        }
    }


//      每日推荐歌曲
    fun getDailySong() = fire(Dispatchers.IO) {
        val timestamp = System.currentTimeMillis()
        val response = mNetwork.getDailySong(timestamp)
        Log.d("test","仓库层，每日推荐歌单"+response.toString())
        if (response.code == 200) {
            val result = response.data.dailySongs
//            Log.d("test","仓库层，每日推荐"+result.toString())
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${response.code}"))
        }
    }
//  每日推荐歌单
    fun getDailyRPlayList() = fire(Dispatchers.IO) {
        val timecurrentTimeMillis = System.currentTimeMillis()
        val response = mNetwork.getDailyRPlayList(timecurrentTimeMillis)
        if (response.code == 200) {
            val result = response.recommend.filter { it.creator.userType != 10 }
            Result.success(result)
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