package com.example.alittlemusic.baseClass

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.drake.brv.BR
import com.drake.brv.utils.BRV
import com.example.alittlemusic.R
import com.example.alittlemusic.data.logic.model.Profile
import com.lzx.starrysky.StarrySky
import com.lzx.starrysky.notification.INotification
import com.lzx.starrysky.notification.NotificationConfig
import com.lzx.starrysky.notification.NotificationManager
import com.lzx.starrysky.notification.imageloader.ImageLoaderCallBack
import com.lzx.starrysky.notification.imageloader.ImageLoaderStrategy
import com.lzx.starrysky.utils.toSdcardPath
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

    @Override
    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        BRV.modelId = BR.viewmodel


        val notificationConfig = NotificationConfig.create {
            targetClass { "com.example.alittlemusic.NotificationReceiver" }
            targetClassBundle {
                val bundle = Bundle()
                bundle.putString("title", "我是点击通知栏转跳带的参数")
                bundle.putString("targetClass", "com.example.alittlemusic.ui.player.PlayerActivity")
                //参数自带当前音频播放信息，不用自己传
                return@targetClassBundle bundle
            }
            pendingIntentMode { NotificationConfig.MODE_BROADCAST }
            smallIconRes { R.drawable.ic_music }
        }
        StarrySky.init(this)
            .setOpenCache(true)
            .setCacheDestFileDir("AlittleMusicCache/".toSdcardPath())
            .setCacheMaxBytes(1024 * 1024 * 1024)  //设置缓存上限，默认 512 * 1024 * 1024
            .setImageLoader(object : ImageLoaderStrategy {
                //使用自定义图片加载器
                override fun loadImage(context: Context, url: String?, callBack: ImageLoaderCallBack) {
                    Glide.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap?>() {
                        override fun onLoadCleared(placeholder: Drawable?) {}

                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                            callBack.onBitmapLoaded(resource)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            callBack.onBitmapFailed(errorDrawable)
                        }
                    })
                }
            })
            .setNotificationSwitch(true)
            .setNotificationType(INotification.CUSTOM_NOTIFICATION)
            .setNotificationConfig(notificationConfig)
            .apply()

        //val Context.dataStore: DataStore<Preferences> by preferenceDataStore(name = "cookie")
    }
}