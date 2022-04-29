package com.example.alittlemusic.data.logic.network.Repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.liveData
import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.data.logic.network.mNetwork
import kotlinx.coroutines.Dispatchers
import java.util.prefs.Preferences
import kotlin.coroutines.CoroutineContext


/**
 *
 * @author liuxin
 * Created on 2022/4/10
 * @description：---
 */
object UserRepository {
    fun login(phone : String,password : String,login_m: Int) = fire(Dispatchers.IO){
//        var loginResponse
        val loginResponse = when(login_m) {
            1->  mNetwork.phoneLogin(phone,password)
            2->  mNetwork.emailLogin(phone,password)
            else -> mNetwork.phoneLogin(phone,password) //默认手机登录
        }
        Log.d("test", "登录" +phone + password)
        if (loginResponse.code == 200) {
            val result = loginResponse
            MyApplication.login_info = result.profile
            MyApplication.cookie = result.cookie
            Result.success(result)
        } else {
            Result.failure(RuntimeException("response status is ${loginResponse.code}"))
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