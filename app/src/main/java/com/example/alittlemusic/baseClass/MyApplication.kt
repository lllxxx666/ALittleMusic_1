package com.example.alittlemusic.baseClass

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.drake.brv.BR
import com.drake.brv.utils.BRV
import com.example.alittlemusic.data.logic.model.Profile
import com.lzx.starrysky.StarrySky
import retrofit2.http.POST

/**
 * 版权：KING公司 版权所有
 *
 * @author liuxin
 * Created on 2022/3/28
 * @description：---
 */
open class MyApplication: Application() {
    companion object{
        @SuppressLint("StaticFiledLeak")
        lateinit var context: Context

        @SuppressLint("StaticFiledLeak")
        lateinit var login_info: Profile

        @SuppressLint("StaticFiledLeak")
        lateinit var cookie: String

        @SuppressLint("StaticFiledLeak")
        const val fakeCookie: String = "MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/clientlog;;MUSIC_U=7d384af7d1b0908e7b6f25de5142a9c59bf2ac5bc68de572a685c2dc524064cc993166e004087dd34dfc7c0121b219268371295cf35bfce2f83a9317205494d650e4c3e6b3c397247a561ba977ae766d; Max-Age=1296000; Expires=Fri, 06 May 2022 22:54:08 GMT; Path=/;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/clientlog;;__remember_me=true; Max-Age=1296000; Expires=Fri, 06 May 2022 22:54:08 GMT; Path=/;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/openapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/openapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/feedback;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/feedback;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/feedback;;MUSIC_SNS=; Max-Age=0; Expires=Thu, 21 Apr 2022 22:54:08 GMT; Path=/;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/feedback;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/wapi/feedback;;__csrf=8703f63673a4cd6a7d98a21e22857f2f; Max-Age=1296010; Expires=Fri, 06 May 2022 22:54:18 GMT; Path=/;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/weapi/feedback;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/api/clientlog;;MUSIC_A_T=1528470355176; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/eapi/clientlog;;MUSIC_R_T=1528470481642; Max-Age=2147483647; Expires=Wed, 10 May 2090 02:08:15 GMT; Path=/neapi/clientlog;"     //获取每日推荐歌单

        @SuppressLint("StaticFiledLeak")
        const val uid = 1482073429

        @SuppressLint("StaticFiledLeak")
        lateinit var CollectedList: ArrayList<Long>
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        BRV.modelId = BR.viewmodel

        StarrySky.init(this).apply()

        //val Context.dataStore: DataStore<Preferences> by preferenceDataStore(name = "cookie")
    }
}