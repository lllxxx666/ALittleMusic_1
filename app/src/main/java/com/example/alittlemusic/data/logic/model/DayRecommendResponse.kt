package com.example.alittlemusic.data.logic.model
import com.google.gson.annotations.SerializedName


/**
 *
 * @author liuxin
 * Created on 2022/4/14
 * @description：---
 */
data class DayRecommendResponse (
    @SerializedName("code")
    val code: Number,
    @SerializedName("featureFirst")
    val featureFirst: Boolean,
    @SerializedName("haveRcmdSongs")
    val haveRcmdSongs: Boolean,
    @SerializedName("recommend")
    val recommend: List<Recommend>,
    @SerializedName("msg")
    val msg: String,
){


    data class Recommend(
        @SerializedName("alg")
        val alg: String,
        @SerializedName("copywriter")
        val copywriter: String,
        @SerializedName("createTime")
        val createTime: Long,
        @SerializedName("creator")
        val creator: Creator,
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("playcount")
        val playcount: Long,
        @SerializedName("trackCount")
        val trackCount: Int,
        @SerializedName("type")
        val type: Int,
        @SerializedName("userId")
        val userId: Int
    )

    data class Creator(
        @SerializedName("accountStatus")
        val accountStatus: Int,
        @SerializedName("authStatus")
        val authStatus: Int,
        @SerializedName("authority")
        val authority: Int,
        @SerializedName("avatarImgId")
        val avatarImgId: Long,
        @SerializedName("avatarImgIdStr")
        val avatarImgIdStr: String,
        @SerializedName("avatarUrl")
        val avatarUrl: String,
        @SerializedName("backgroundImgId")
        val backgroundImgId: Long,
        @SerializedName("backgroundImgIdStr")
        val backgroundImgIdStr: String,
        @SerializedName("backgroundUrl")
        val backgroundUrl: String,
        @SerializedName("birthday")
        val birthday: Int,
        @SerializedName("city")
        val city: Int,
        @SerializedName("defaultAvatar")
        val defaultAvatar: Boolean,
        @SerializedName("description")
        val description: String,
        @SerializedName("detailDescription")
        val detailDescription: String,
        @SerializedName("djStatus")
        val djStatus: Int,
        @SerializedName("expertTags")
        val expertTags: Any,
        @SerializedName("followed")
        val followed: Boolean,
        @SerializedName("gender")
        val gender: Int,
        @SerializedName("mutual")
        val mutual: Boolean,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("province")
        val province: Int,
        @SerializedName("remarkName")
        val remarkName: Any,
        @SerializedName("signature")
        val signature: String,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("userType")
        val userType: Int,
        @SerializedName("vipType")
        val vipType: Int
    )
}


data class DayRecommendSongResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: DayRecommendSong
){

    data class DayRecommendSong(
        @SerializedName("dailySongs")
        val dailySongs: List<DailySong>,
        @SerializedName("orderSongs")
        val orderSongs: List<Any>,
        @SerializedName("recommendReasons")
        val recommendReasons: List<RecommendReason>
    )

    data class DailySong(
        @SerializedName("a")
        val a: Any,
        @SerializedName("al")
        val al: Al,
        @SerializedName("alg")
        val alg: String,
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
        @SerializedName("privilege")
        val privilege: Privilege,
        @SerializedName("pst")
        val pst: Int,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("reason")
        val reason: String,
        @SerializedName("rt")
        val rt: Any,
        @SerializedName("rtUrl")
        val rtUrl: Any,
        @SerializedName("rtUrls")
        val rtUrls: List<Any>,
        @SerializedName("rtype")
        val rtype: Int,
        @SerializedName("rurl")
        val rurl: Any,
        @SerializedName("s_ctrp")
        val sCtrp: String,
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

    data class RecommendReason(
        @SerializedName("reason")
        val reason: String,
        @SerializedName("songId")
        val songId: Int
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


    data class Privilege(
        @SerializedName("chargeInfoList")
        val chargeInfoList: List<ChargeInfo>,
        @SerializedName("cp")
        val cp: Int,
        @SerializedName("cs")
        val cs: Boolean,
        @SerializedName("dl")
        val dl: Int,
        @SerializedName("downloadMaxbr")
        val downloadMaxbr: Int,
        @SerializedName("fee")
        val fee: Int,
        @SerializedName("fl")
        val fl: Int,
        @SerializedName("flag")
        val flag: Int,
        @SerializedName("freeTrialPrivilege")
        val freeTrialPrivilege: FreeTrialPrivilege,
        @SerializedName("id")
        val id: Int,
        @SerializedName("maxbr")
        val maxbr: Int,
        @SerializedName("payed")
        val payed: Int,
        @SerializedName("pl")
        val pl: Int,
        @SerializedName("playMaxbr")
        val playMaxbr: Int,
        @SerializedName("preSell")
        val preSell: Boolean,
        @SerializedName("rscl")
        val rscl: Int,
        @SerializedName("sp")
        val sp: Int,
        @SerializedName("st")
        val st: Int,
        @SerializedName("subp")
        val subp: Int,
        @SerializedName("toast")
        val toast: Boolean
    )

    data class ChargeInfo(
        @SerializedName("chargeMessage")
        val chargeMessage: Any,
        @SerializedName("chargeType")
        val chargeType: Int,
        @SerializedName("chargeUrl")
        val chargeUrl: Any,
        @SerializedName("rate")
        val rate: Int
    )

    data class FreeTrialPrivilege(
        @SerializedName("resConsumable")
        val resConsumable: Boolean,
        @SerializedName("userConsumable")
        val userConsumable: Boolean
    )
}
