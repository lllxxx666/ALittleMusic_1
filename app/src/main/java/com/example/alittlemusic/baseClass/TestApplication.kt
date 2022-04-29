package com.example.alittlemusic.baseClass

import android.app.Application
import com.lzx.starrysky.StarrySky

/**
 *
 * @author liuxin
 * Created on 2022/4/28
 * @description：---
 */
open class TestApplication : Application() {
    @Override
    override fun onCreate() {
        super.onCreate()
        StarrySky.init(this).apply()
    }
}