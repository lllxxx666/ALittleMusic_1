package com.example.alittlemusic.data.logic.model
import com.google.gson.annotations.SerializedName


/**
 *
 * @author liuxin
 * Created on 2022/4/22
 * @description：---
 */
data class RecentPlayResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
){
    data class Data(
        @SerializedName("list")
        val list: List<MyList>,
        @SerializedName("total")
        val total: Int
    )

    data class MyList(
        @SerializedName("data")
        val `data`: DataX,
        @SerializedName("playTime")
        val playTime: Long,
        @SerializedName("resourceId")
        val resourceId: String,
        @SerializedName("resourceType")
        val resourceType: String
    )

    data class DataX(
        @SerializedName("a")
        val a: Any,
        @SerializedName("al")
        val al: Al,
        @SerializedName("alia")
        val alia: List<Any>,
        @SerializedName("ar")
        val ar: List<Ar>,
        @SerializedName("cd")
        val cd: String,
        @SerializedName("cf")
        val cf: String,
        @SerializedName("copyright")
        val copyright: Int,
        @SerializedName("cp")
        val cp: Int,
        @SerializedName("crbt")
        val crbt: Any,
        @SerializedName("djId")
        val djId: Int,
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("fee")
        val fee: Int,
        @SerializedName("ftype")
        val ftype: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("mark")
        val mark: Int,
        @SerializedName("mst")
        val mst: Int,
        @SerializedName("mv")
        val mv: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("no")
        val no: Int,
        @SerializedName("noCopyrightRcmd")
        val noCopyrightRcmd: Any,
        @SerializedName("originCoverType")
        val originCoverType: Int,
        @SerializedName("originSongSimpleData")
        val originSongSimpleData: Any,
        @SerializedName("pop")
        val pop: Int,
        @SerializedName("pst")
        val pst: Int,
        @SerializedName("publishTime")
        val publishTime: Int,
        @SerializedName("rt")
        val rt: String,
        @SerializedName("rtUrl")
        val rtUrl: Any,
        @SerializedName("rtUrls")
        val rtUrls: List<Any>,
        @SerializedName("rtype")
        val rtype: Int,
        @SerializedName("rurl")
        val rurl: Any,
        @SerializedName("s_id")
        val sId: Int,
        @SerializedName("single")
        val single: Int,
        @SerializedName("st")
        val st: Int,
        @SerializedName("t")
        val t: Int,
        @SerializedName("v")
        val v: Int
    )

    data class Al(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("pic")
        val pic: Long,
        @SerializedName("pic_str")
        val picStr: String,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("tns")
        val tns: List<Any>
    )

    data class Ar(
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("tns")
        val tns: List<Any>
    )
}



