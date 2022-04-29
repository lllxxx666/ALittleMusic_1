package com.example.alittlemusic.data.logic.model
import com.google.gson.annotations.SerializedName


/**
 *
 * @author liuxin
 * Created on 2022/4/18
 * @description：---
 */
data class UserPlayListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("more")
    val more: Boolean,
    @SerializedName("playlist")
    val playlist: List<Playlist>,
    @SerializedName("version")
    val version: String
){

    data class Playlist(
        @SerializedName("adType")
        val adType: Int,
        @SerializedName("anonimous")
        val anonimous: Boolean,
        @SerializedName("artists")
        val artists: Any,
        @SerializedName("backgroundCoverId")
        val backgroundCoverId: Int,
        @SerializedName("backgroundCoverUrl")
        val backgroundCoverUrl: Any,
        @SerializedName("cloudTrackCount")
        val cloudTrackCount: Int,
        @SerializedName("commentThreadId")
        val commentThreadId: String,
        @SerializedName("coverImgId")
        val coverImgId: Long,
        @SerializedName("coverImgId_str")
        val coverImgIdStr: String,
        @SerializedName("coverImgUrl")
        val coverImgUrl: String,
        @SerializedName("createTime")
        val createTime: Long,
        @SerializedName("creator")
        val creator: Creator,
        @SerializedName("description")
        val description: Any,
        @SerializedName("englishTitle")
        val englishTitle: Any,
        @SerializedName("highQuality")
        val highQuality: Boolean,
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("newImported")
        val newImported: Boolean,
        @SerializedName("opRecommend")
        val opRecommend: Boolean,
        @SerializedName("ordered")
        val ordered: Boolean,
        @SerializedName("playCount")
        val playCount: Int,
        @SerializedName("privacy")
        val privacy: Int,
        @SerializedName("recommendInfo")
        val recommendInfo: Any,
        @SerializedName("shareStatus")
        val shareStatus: Any,
        @SerializedName("sharedUsers")
        val sharedUsers: Any,
        @SerializedName("specialType")
        val specialType: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("subscribed")
        val subscribed: Boolean,
        @SerializedName("subscribedCount")
        val subscribedCount: Int,
        @SerializedName("subscribers")
        val subscribers: List<Any>,
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("titleImage")
        val titleImage: Int,
        @SerializedName("titleImageUrl")
        val titleImageUrl: Any,
        @SerializedName("totalDuration")
        val totalDuration: Int,
        @SerializedName("trackCount")
        val trackCount: Int,
        @SerializedName("trackNumberUpdateTime")
        val trackNumberUpdateTime: Long,
        @SerializedName("trackUpdateTime")
        val trackUpdateTime: Long,
        @SerializedName("tracks")
        val tracks: Any,
        @SerializedName("updateFrequency")
        val updateFrequency: Any,
        @SerializedName("updateTime")
        val updateTime: Long,
        @SerializedName("userId")
        val userId: Int
    )

    data class Creator(
        @SerializedName("accountStatus")
        val accountStatus: Int,
        @SerializedName("anchor")
        val anchor: Boolean,
        @SerializedName("authStatus")
        val authStatus: Int,
        @SerializedName("authenticationTypes")
        val authenticationTypes: Int,
        @SerializedName("authority")
        val authority: Int,
        @SerializedName("avatarDetail")
        val avatarDetail: Any,
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
        val expertTags: List<String>,
        @SerializedName("experts")
        val experts: Any,
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