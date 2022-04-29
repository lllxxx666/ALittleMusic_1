package com.example.alittlemusic.data.logic.network

import com.example.alittlemusic.data.logic.network.service.*
import com.example.retrofittest.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object mNetwork {

    private val userService = ServiceCreator.create(UserService::class.java)
    suspend fun phoneLogin(phone: String, password: String) = userService.phoneLogin(phone,password).await()
    suspend fun emailLogin(email: String, password: String) = userService.emailLogin(email,password).await()
    suspend fun logout() = userService.logout().await()
    suspend fun getUserInfo(uid: Number) = userService.getUserInfo(uid).await()
    suspend fun getAccountInfo() = userService.getAccountInfo().await()
    suspend fun updateUserInfo(gender: Number, birthday: String,nickname: String,signature: String)
                = userService.updateUserInfo(gender,birthday,nickname,signature).await()
    suspend fun getUserPlayList(uid: Int) = userService.getUserPlayList(uid).await()
    suspend fun likeSong(id: Int) = userService.likeSong(id).await()    //调用此接口 , 传入音乐 id, 可喜欢该音乐
    suspend fun getUserLikeList(uid: Int) = userService.getUserLikeList(uid).await()    //调用此接口 , 传入用户 id, 可获取已喜欢音乐 id 列表(id 数组)

    private val commonService = ServiceCreator.create(CommonService::class.java)
    suspend fun getBanner() = commonService.getBanner().await()
//    suspend fun search(keywords: String) = commonService.search (keywords).await()

    private val searchService = ServiceCreator.create(SearchService::class.java)
    suspend fun search(keywords: String) = searchService.search (keywords).await()
    suspend fun searchSongs(keywords: String) = searchService.searchSongs (keywords).await()
    suspend fun searchPlayList(keywords: String) = searchService.searchPlayList (keywords).await()

    private val songService = ServiceCreator.create(SongService::class.java)
    suspend fun getSongUrl(id: Int) = songService.getSongUrl(id).await()
    suspend fun getSongDetail(ids: Int) = songService.getSongDetail(ids).await()
    suspend fun checkSong(id: Int) = songService.checkSong(id).await()
    suspend fun getLyric(id: Int) = songService.getLyric(id).await()
    suspend fun likeSong(id: Int,like: Boolean) = songService.likeSong(id,like).await()
    suspend fun subSong(op: String,pid: Int,tracks: Int) = songService.subSong(op,pid,tracks).await()
    suspend fun getRecentSongs(timestamp: Long)= songService.getRecentSongs(timestamp).await()      //获取最近播放-歌曲，必须登录后使用
    suspend fun getDailySong(timestamp : Long) = songService.getDailySong(timestamp).await()     //获得每日推荐歌曲

    private val playListService = ServiceCreator.create(PlayListService::class.java)
    suspend fun getRPlayList(limit: Int) = playListService.getRPlayList(limit).await()
    suspend fun getTopListDetail() = playListService.getTopListDetail().await()
    suspend fun getPlayListAll(id: Number) = playListService.getPlayListAll(id).await()     // 获取歌单所有歌曲
    suspend fun subPlayList(t: Int,id: Int) = playListService.subPlayList(t,id).await()      //收藏/取消收藏歌单
    suspend fun getRelatedPlayList(id: Number) = playListService.getRelatedPlayList(id).await()      //获取某个歌单的相关歌单，必须登录后使用
    suspend fun getDailyRPlayList(timecurrentTimeMillis:Long) = playListService.getDailyRPlayList(timecurrentTimeMillis).await()      //获取每日推荐歌单，必须登录后使用
    suspend fun getUserPlayList(uid: Int,timestamp: Long) = playListService.getUserPlayList(uid,timestamp).await()      //获取每日推荐歌单，必须登录后使用
    suspend fun getRecentPlayList(timestamp: Long)= playListService.getRecentPlayList(timestamp).await()      //获取最近播放-歌单，必须登录后使用
    suspend fun getBPlayList(tags: String?)= playListService.getBPlayList(tags).await()      //获得精品歌单


    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }



}