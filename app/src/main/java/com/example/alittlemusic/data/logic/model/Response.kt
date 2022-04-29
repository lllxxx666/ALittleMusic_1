package com.example.alittlemusic.data.logic.model
import com.google.gson.annotations.SerializedName


/**
 *
 * @author liuxin
 * Created on 2022/4/26
 * @description：---
 */
data class Response(
    @SerializedName("code")
    val code: Int,
    var message: String
)