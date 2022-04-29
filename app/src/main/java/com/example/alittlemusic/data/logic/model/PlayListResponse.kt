package com.example.alittlemusic.data.logic.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlayListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("songs")
    val songs: List<Song>
)
data class Song(
    @SerializedName("al")
    val al: Al,
    @SerializedName("ar")
    val ar: List<Ar>,
    @SerializedName("cd")
    val cd: String,
    @SerializedName("copyright")
    val copyright: Int,
    @SerializedName("fee")
    val fee: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mark")
    val mark: Number,
    @SerializedName("mst")
    val mst: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("originCoverType")
    val originCoverType: Int,
    @SerializedName("originSongSimpleData")
    val originSongSimpleData: Any,
    @SerializedName("publishTime")
    val publishTime: Long,
    @SerializedName("resourceState")
    val resourceState: Boolean,
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
    @SerializedName("st")
    val st: Int,
    @SerializedName("t")
    val t: Int,
    @SerializedName("tagPicList")
    val tagPicList: Any,
    @SerializedName("tns")
    val tns: List<String>,
    @SerializedName("v")
    val v: Int,
    @SerializedName("version")
    val version: Int
): Serializable

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
): Serializable

data class Ar(
    @SerializedName("alias")
    val alias: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tns")
    val tns: List<Any>
): Serializable
data class ArtistX(
    @SerializedName("albumSize")
    val albumSize: Int,
    @SerializedName("alias")
    val alias: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img1v1")
    val img1v1: Int,
    @SerializedName("img1v1Url")
    val img1v1Url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picId")
    val picId: Int,
    @SerializedName("picUrl")
    val picUrl: Any,
    @SerializedName("trans")
    val trans: Any
)

data class Artist(
    @SerializedName("albumSize")
    val albumSize: Int,
    @SerializedName("alias")
    val alias: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img1v1")
    val img1v1: Int,
    @SerializedName("img1v1Url")
    val img1v1Url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picId")
    val picId: Int,
    @SerializedName("picUrl")
    val picUrl: Any,
    @SerializedName("trans")
    val trans: Any
)

// 推荐歌单响应
data class RPlayListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val result: List<Result>
){
    data class Result(
        @SerializedName("copywriter")
        val copywriter: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("playCount")
        val playCount: Int,
        @SerializedName("trackCount")
        val trackCount: Int,
        @SerializedName("type")
        val type: Int
    )
}

//排行榜响应
data class TopListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("list")
    val toplist: List<TopList>
) {
    data class TopList(
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
        val creator: Any,
        @SerializedName("description")
        val description: String,
        @SerializedName("englishTitle")
        val englishTitle: Any,
        @SerializedName("highQuality")
        val highQuality: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("newImported")
        val newImported: Boolean,
        @SerializedName("opRecommend")
        val opRecommend: Boolean,
        @SerializedName("ordered")
        val ordered: Boolean,
        @SerializedName("playCount")
        val playCount: Long,
        @SerializedName("privacy")
        val privacy: Int,
        @SerializedName("recommendInfo")
        val recommendInfo: Any,
        @SerializedName("specialType")
        val specialType: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("subscribed")
        val subscribed: Any,
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
        @SerializedName("ToplistType")
        val toplistType: String,
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
        val updateFrequency: String,
        @SerializedName("updateTime")
        val updateTime: Long,
        @SerializedName("userId")
        val userId: Int
    )
}

data class TopListDetailResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("list")
    val toplist: List<TopList>
){

    data class TopList(
        @SerializedName("backgroundCoverUrl")
        val backgroundCoverUrl: Any,
        @SerializedName("cloudTrackCount")
        val cloudTrackCount: Int,
        @SerializedName("coverImgId")
        val coverImgId: Long,
        @SerializedName("coverImgId_str")
        val coverImgIdStr: String,
        @SerializedName("coverImgUrl")
        val coverImgUrl: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("englishTitle")
        val englishTitle: Any,
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
        val playCount: Long,
        @SerializedName("recommendInfo")
        val recommendInfo: Any,
        @SerializedName("specialType")
        val specialType: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("subscribed")
        val subscribed: Any,
        @SerializedName("subscribedCount")
        val subscribedCount: Int,
        @SerializedName("subscribers")
        val subscribers: List<Any>,
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("ToplistType")
        val toplistType: String,
        @SerializedName("totalDuration")
        val totalDuration: Int,
        @SerializedName("trackCount")
        val trackCount: Int,
        @SerializedName("trackNumberUpdateTime")
        val trackNumberUpdateTime: Long,
        @SerializedName("trackUpdateTime")
        val trackUpdateTime: Long,
        @SerializedName("tracks")
        val tracks: List<Track>,
        @SerializedName("updateFrequency")
        val updateFrequency: String,
        @SerializedName("updateTime")
        val updateTime: Long,
        @SerializedName("userId")
        val userId: Number
    )

    data class Track(
        @SerializedName("first")
        val first: String,
        @SerializedName("second")
        val second: String
    )
}
//精品歌单
data class BoutiquePlaylistResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("lasttime")
    val lasttime: Long,
    @SerializedName("more")
    val more: Boolean,
    @SerializedName("playlists")
    val playlists: List<Playlists>,
    @SerializedName("total")
    val total: Int
){

    data class Playlists(
        @SerializedName("adType")
        val adType: Int,
        @SerializedName("anonimous")
        val anonimous: Boolean,
        @SerializedName("cloudTrackCount")
        val cloudTrackCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("commentThreadId")
        val commentThreadId: String,
        @SerializedName("copywriter")
        val copywriter: String,
        @SerializedName("coverImgId")
        val coverImgId: Long,
        @SerializedName("coverImgId_str")
        val coverImgIdStr: String,
        @SerializedName("coverImgUrl")
        val coverImgUrl: String,
        @SerializedName("coverStatus")
        val coverStatus: Int,
        @SerializedName("createTime")
        val createTime: Long,
        @SerializedName("description")
        val description: String,
        @SerializedName("highQuality")
        val highQuality: Boolean,
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("newImported")
        val newImported: Boolean,
        @SerializedName("ordered")
        val ordered: Boolean,
        @SerializedName("playCount")
        val playCount: Int,
        @SerializedName("privacy")
        val privacy: Int,
        @SerializedName("recommendInfo")
        val recommendInfo: Any,
        @SerializedName("shareCount")
        val shareCount: Int,
        @SerializedName("specialType")
        val specialType: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("subscribed")
        val subscribed: Boolean,
        @SerializedName("subscribedCount")
        val subscribedCount: Int,
        @SerializedName("tag")
        val tag: String,
        @SerializedName("tags")
        val tags: List<String>,
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
        @SerializedName("updateTime")
        val updateTime: Long,
        @SerializedName("userId")
        val userId: Int
    )
}

//最近播放-歌单
data class RecentPlistResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String
) {
    data class Data(
        @SerializedName("list")
        val list: List<songs>,
        @SerializedName("total")
        val total: Int
    ){
        data class songs(
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
            val publishTime: Long,
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

}



