package com.example.alittlemusic.data.logic.network.service

import com.example.alittlemusic.data.logic.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * @author liuxin
 * Created on 2022/3/31
 * @description：---
 */
interface UserService {

    @FormUrlEncoded
    @POST("login/cellphone")
    fun phoneLogin(@Field("phone") phone : String, @Field("password") password : String): Call<LoginResponse>

    @GET("login")
    fun emailLogin(@Query("email") email : String,@Query("password") password : String): Call<LoginResponse>

    @GET("logout")
    fun logout(): Call<Any>

    @GET("user/detail")
    fun getUserInfo(@Query("uid") uid : Number,): Call<Any>

    @GET("user/account")
    fun getAccountInfo(): Call<Any>

    @GET("user/update")
    fun updateUserInfo(@Query("gender") gender: Number,@Query("birthday")  birthday: String,
                       @Query("nickname") nickname: String,@Query("signature")  signature: String): Call<Any>

    @GET("user/playlist")
    fun getUserPlayList(uid: Int): Call<Any>

    @GET("likelist")
    fun getUserLikeList(@Query("uid") uid: Int): Call<Any>

    @GET("like")
    fun likeSong(@Query("id") id: Int): Call<Any>
}