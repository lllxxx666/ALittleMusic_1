package com.example.alittlemusic.data.logic.model
import com.google.gson.annotations.SerializedName


/**
 *
 * @author liuxin
 * Created on 2022/4/10
 * @description：---
 */
data class SearchResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val result: AllResult
)
//  综合搜索的结果
data class AllResult(
    @SerializedName("album")
    val album: SongsResult.Album,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("code")
    val code: Int,
    @SerializedName("playList")
    val playList: PlayList,
    @SerializedName("song")
    val song: Song
){

    data class PlayList(
        @SerializedName("more")
        val more: Boolean,
        @SerializedName("moreText")
        val moreText: String,
        @SerializedName("playLists")
        val playLists: List<PlayLists>,
        @SerializedName("resourceIds")
        val resourceIds: List<Long>
    )

    data class Song(
        @SerializedName("more")
        val more: Boolean,
        @SerializedName("moreText")
        val moreText: String,
        @SerializedName("songs")
        val songs: List<SongX>
    )

    data class PlayLists(
        @SerializedName("bookCount")
        val bookCount: Int,
        @SerializedName("coverImgUrl")
        val coverImgUrl: String,
        @SerializedName("creator")
        val creator: Creator,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("officialTags")
        val officialTags: List<String>,
        @SerializedName("playCount")
        val playCount: Int,
        @SerializedName("track")
        val track: Track,
        @SerializedName("trackCount")
        val trackCount: Int
    )

    data class Creator(
        @SerializedName("authStatus")
        val authStatus: Int,
        @SerializedName("avatarUrl")
        val avatarUrl: String,
        @SerializedName("expertTags")
        val expertTags: Any,
        @SerializedName("experts")
        val experts: Any,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("userType")
        val userType: Int
    )

    data class Track(
        @SerializedName("album")
        val album: AlbumX,
        @SerializedName("alias")
        val alias: List<String>,
        @SerializedName("artists")
        val artists: List<ArtistXXX>,
        @SerializedName("audition")
        val audition: Any,
        @SerializedName("commentThreadId")
        val commentThreadId: String,
        @SerializedName("copyFrom")
        val copyFrom: String,
        @SerializedName("copyright")
        val copyright: Int,
        @SerializedName("copyrightId")
        val copyrightId: Int,
        @SerializedName("crbt")
        val crbt: Any,
        @SerializedName("dayPlays")
        val dayPlays: Int,
        @SerializedName("disc")
        val disc: String,
        @SerializedName("duration")
        val duration: Int,
        @SerializedName("fee")
        val fee: Int,
        @SerializedName("ftype")
        val ftype: Int,
        @SerializedName("hearTime")
        val hearTime: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("mp3Url")
        val mp3Url: Any,
        @SerializedName("mvid")
        val mvid: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("no")
        val no: Int,
        @SerializedName("playedNum")
        val playedNum: Int,
        @SerializedName("popularity")
        val popularity: Int,
        @SerializedName("position")
        val position: Int,
        @SerializedName("ringtone")
        val ringtone: String,
        @SerializedName("rtUrl")
        val rtUrl: Any,
        @SerializedName("rtUrls")
        val rtUrls: List<Any>,
        @SerializedName("rtype")
        val rtype: Int,
        @SerializedName("rurl")
        val rurl: Any,
        @SerializedName("score")
        val score: Int,
        @SerializedName("starred")
        val starred: Boolean,
        @SerializedName("starredNum")
        val starredNum: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("transNames")
        val transNames: List<String>
    )

    data class AlbumX(
        @SerializedName("alias")
        val alias: List<String>,
        @SerializedName("artist")
        val artist: ArtistX,
        @SerializedName("artists")
        val artists: List<ArtistXX>,
        @SerializedName("blurPicUrl")
        val blurPicUrl: String,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("commentThreadId")
        val commentThreadId: String,
        @SerializedName("company")
        val company: String,
        @SerializedName("companyId")
        val companyId: Int,
        @SerializedName("copyrightId")
        val copyrightId: Int,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("pic")
        val pic: Long,
        @SerializedName("picId")
        val picId: Long,
        @SerializedName("picId_str")
        val picIdStr: String,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("size")
        val size: Int,
        @SerializedName("songs")
        val songs: List<Any>,
        @SerializedName("status")
        val status: Int,
        @SerializedName("tags")
        val tags: String,
        @SerializedName("type")
        val type: String
    )

    data class ArtistXXX(
        @SerializedName("albumSize")
        val albumSize: Int,
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("img1v1Id")
        val img1v1Id: Int,
        @SerializedName("img1v1Url")
        val img1v1Url: String,
        @SerializedName("musicSize")
        val musicSize: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("picId")
        val picId: Int,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("trans")
        val trans: String
    )

    data class ArtistX(
        @SerializedName("albumSize")
        val albumSize: Int,
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("img1v1Id")
        val img1v1Id: Int,
        @SerializedName("img1v1Url")
        val img1v1Url: String,
        @SerializedName("musicSize")
        val musicSize: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("picId")
        val picId: Int,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("trans")
        val trans: String
    )

    data class ArtistXX(
        @SerializedName("albumSize")
        val albumSize: Int,
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("img1v1Id")
        val img1v1Id: Int,
        @SerializedName("img1v1Url")
        val img1v1Url: String,
        @SerializedName("musicSize")
        val musicSize: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("picId")
        val picId: Int,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("trans")
        val trans: String
    )
    data class SongX(
        @SerializedName("al")
        val al: Al,
        @SerializedName("alg")
        val alg: String,
        @SerializedName("alia")
        val alia: List<String>,
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
        @SerializedName("officialTags")
        val officialTags: List<Any>,
        @SerializedName("originCoverType")
        val originCoverType: Int,
        @SerializedName("originSongSimpleData")
        val originSongSimpleData: Any,
        @SerializedName("pop")
        val pop: Int,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("recommendText")
        val recommendText: String,
        @SerializedName("resourceState")
        val resourceState: Boolean,
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
        @SerializedName("showRecommend")
        val showRecommend: Boolean,
        @SerializedName("single")
        val single: Int,
        @SerializedName("specialTags")
        val specialTags: List<Any>,
        @SerializedName("version")
        val version: Int
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
        val picUrl: String
    )

    data class Ar(
        @SerializedName("alia")
        val alia: List<String>,
        @SerializedName("alias")
        val alias: List<String>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}

data class SearchSongsResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val resultSons: SongsResult
)
//  单曲搜索结果
data class SongsResult(
    @SerializedName("hasMore")
    val hasMore: Boolean,
    @SerializedName("songCount")
    val songCount: Int,
    @SerializedName("songs")
    val songs: List<Song>
){
    data class Song(
        @SerializedName("album")
        val album: Album,
        @SerializedName("alias")
        val alias: List<String>,
        @SerializedName("artists")
        val artists: List<ArtistX>,
        @SerializedName("copyrightId")
        val copyrightId: Int,
        @SerializedName("duration")
        val duration: Int,
        @SerializedName("fee")
        val fee: Int,
        @SerializedName("ftype")
        val ftype: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("mark")
        val mark: Number,
        @SerializedName("name")
        val name: String,
        @SerializedName("rtype")
        val rtype: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("transNames")
        val transNames: List<String>
    )

    data class Album(
        @SerializedName("alia")
        val alia: List<String>,
        @SerializedName("artist")
        val artist: Artist,
        @SerializedName("copyrightId")
        val copyrightId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("mark")
        val mark: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("picId")
        val picId: Long,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("size")
        val size: Int,
        @SerializedName("status")
        val status: Int
    )

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
}

data class SearchPlayListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val resultPlayList: PlayListResult
)
//  歌单搜索结果
data class PlayListResult(
    @SerializedName("hasMore")
    val hasMore: Boolean,
    @SerializedName("playlistCount")
    val playlistCount: Int,
    @SerializedName("playlists")
    val playlists: List<Playlists>,
    @SerializedName("searchQcReminder")
    val searchQcReminder: Any
){
    data class Playlists(
        @SerializedName("action")
        val action: String,
        @SerializedName("actionType")
        val actionType: String,
        @SerializedName("alg")
        val alg: String,
        @SerializedName("bookCount")
        val bookCount: Int,
        @SerializedName("coverImgUrl")
        val coverImgUrl: String,
        @SerializedName("creator")
        val creator: Creator,
        @SerializedName("description")
        val description: String,
        @SerializedName("highQuality")
        val highQuality: Boolean,
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("officialTags")
        val officialTags: Any,
        @SerializedName("playCount")
        val playCount: Int,
        @SerializedName("recommendText")
        val recommendText: String,
        @SerializedName("specialType")
        val specialType: Int,
        @SerializedName("subscribed")
        val subscribed: Boolean,
        @SerializedName("track")
        val track: Track,
        @SerializedName("trackCount")
        val trackCount: Int,
        @SerializedName("userId")
        val userId: Long
    )

    data class Creator(
        @SerializedName("authStatus")
        val authStatus: Int,
        @SerializedName("avatarUrl")
        val avatarUrl: String,
        @SerializedName("expertTags")
        val expertTags: Any,
        @SerializedName("experts")
        val experts: Any,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("userId")
        val userId: Long,
        @SerializedName("userType")
        val userType: Int
    )

    data class Track(
        @SerializedName("album")
        val album: Album,
        @SerializedName("alias")
        val alias: List<String>,
        @SerializedName("artists")
        val artists: List<ArtistXX>,
        @SerializedName("audition")
        val audition: Any,
        @SerializedName("commentThreadId")
        val commentThreadId: String,
        @SerializedName("copyFrom")
        val copyFrom: String,
        @SerializedName("copyright")
        val copyright: Int,
        @SerializedName("copyrightId")
        val copyrightId: Int,
        @SerializedName("crbt")
        val crbt: Any,
        @SerializedName("dayPlays")
        val dayPlays: Int,
        @SerializedName("disc")
        val disc: String,
        @SerializedName("duration")
        val duration: Int,
        @SerializedName("fee")
        val fee: Int,
        @SerializedName("ftype")
        val ftype: Int,
        @SerializedName("hearTime")
        val hearTime: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("mp3Url")
        val mp3Url: Any,
        @SerializedName("mvid")
        val mvid: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("no")
        val no: Int,
        @SerializedName("playedNum")
        val playedNum: Int,
        @SerializedName("popularity")
        val popularity: Int,
        @SerializedName("position")
        val position: Int,
        @SerializedName("ringtone")
        val ringtone: String,
        @SerializedName("rtUrl")
        val rtUrl: Any,
        @SerializedName("rtUrls")
        val rtUrls: List<Any>,
        @SerializedName("rtype")
        val rtype: Int,
        @SerializedName("rurl")
        val rurl: Any,
        @SerializedName("score")
        val score: Int,
        @SerializedName("starred")
        val starred: Boolean,
        @SerializedName("starredNum")
        val starredNum: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("transNames")
        val transNames: List<String>
    )

    data class Album(
        @SerializedName("alias")
        val alias: List<String>,
        @SerializedName("artist")
        val artist: Artist,
        @SerializedName("artists")
        val artists: List<ArtistX>,
        @SerializedName("blurPicUrl")
        val blurPicUrl: String,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("commentThreadId")
        val commentThreadId: String,
        @SerializedName("company")
        val company: String,
        @SerializedName("companyId")
        val companyId: Int,
        @SerializedName("copyrightId")
        val copyrightId: Int,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("pic")
        val pic: Long,
        @SerializedName("picId")
        val picId: Long,
        @SerializedName("picId_str")
        val picIdStr: String,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("size")
        val size: Int,
        @SerializedName("songs")
        val songs: List<Any>,
        @SerializedName("status")
        val status: Int,
        @SerializedName("tags")
        val tags: String,
        @SerializedName("transNames")
        val transNames: List<String>,
        @SerializedName("type")
        val type: String
    )

    data class ArtistXX(
        @SerializedName("albumSize")
        val albumSize: Int,
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("img1v1Id")
        val img1v1Id: Int,
        @SerializedName("img1v1Url")
        val img1v1Url: String,
        @SerializedName("musicSize")
        val musicSize: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("picId")
        val picId: Int,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("trans")
        val trans: String
    )

    data class Artist(
        @SerializedName("albumSize")
        val albumSize: Int,
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("img1v1Id")
        val img1v1Id: Int,
        @SerializedName("img1v1Url")
        val img1v1Url: String,
        @SerializedName("musicSize")
        val musicSize: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("picId")
        val picId: Int,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("trans")
        val trans: String
    )

    data class ArtistX(
        @SerializedName("albumSize")
        val albumSize: Int,
        @SerializedName("alias")
        val alias: List<Any>,
        @SerializedName("briefDesc")
        val briefDesc: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("img1v1Id")
        val img1v1Id: Int,
        @SerializedName("img1v1Url")
        val img1v1Url: String,
        @SerializedName("musicSize")
        val musicSize: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("picId")
        val picId: Int,
        @SerializedName("picUrl")
        val picUrl: String,
        @SerializedName("trans")
        val trans: String
    )
}

