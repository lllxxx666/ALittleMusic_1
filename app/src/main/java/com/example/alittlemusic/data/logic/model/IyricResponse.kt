package com.example.alittlemusic.data.logic.model
import com.google.gson.annotations.SerializedName


/**
 *
 * @author liuxin
 * Created on 2022/4/11
 * @description：---
 */
data class lyricResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("klyric")
    val klyric: Klyric,
    @SerializedName("lrc")
    val lrc: Lrc,
    @SerializedName("qfy")
    val qfy: Boolean,
    @SerializedName("sfy")
    val sfy: Boolean,
    @SerializedName("sgc")
    val sgc: Boolean,
    @SerializedName("tlyric")
    val tlyric: Tlyric
)

data class Klyric(
    @SerializedName("lyric")
    val lyric: String,
    @SerializedName("version")
    val version: Int
)

data class Lrc(
    @SerializedName("lyric")
    val lyric: String,
    @SerializedName("version")
    val version: Int
)

data class Tlyric(
    @SerializedName("lyric")
    val lyric: String,
    @SerializedName("version")
    val version: Int
)