package com.example.alittlemusic.data.logic.network.Repository

import androidx.lifecycle.liveData
import com.example.alittlemusic.data.logic.network.mNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 *
 * @author liuxin
 * Created on 2022/3/31
 * @description：---
 */
object Repository {


//    fun searchSongs(query: String) = fire(Dispatchers.IO) {
//        val response = mNetwork.search(query)
//        if (response.code == 200) {
//            val result = response.result
//            Result.success(result)
//        } else {
//            Result.failure(RuntimeException("response status is ${response.code}"))
//        }
//    }
    fun getRecentSongs() = fire(Dispatchers.IO) {
        val timestamp= System.currentTimeMillis()
        val response = mNetwork.getRecentSongs(timestamp)
        if (response.code == 200) {
            val result = response.data
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