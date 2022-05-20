package com.example.alittlemusic.ui

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import com.example.activitytest.BaseActivity
import com.example.alittlemusic.MainActivity
import com.example.alittlemusic.R
import com.example.alittlemusic.ui.login.LoginActivity
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class SplashScreenActivity : Activity() {

    lateinit var mCoroutineScope: CoroutineScope

    private var mKeepOnAtomicBool = AtomicBoolean(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mSplashScreen =  installSplashScreen()
        setContentView(R.layout.activity_splash_screen)


        // 每次UI绘制前，会判断 Splash 是否有必要继续展示在屏幕上；直到不再满足条件时，隐藏Splash。
        mSplashScreen.setKeepVisibleCondition(object : SplashScreen.KeepOnScreenCondition {
            override fun shouldKeepOnScreen(): Boolean {
                return mKeepOnAtomicBool.get()
            }

        })

        // Splash展示完毕的监听方法
        mSplashScreen.setOnExitAnimationListener(object : SplashScreen.OnExitAnimationListener{
            override fun onSplashScreenExit(splashScreenViewProvider: SplashScreenViewProvider) {
                startSplashScreenExit(splashScreenViewProvider)
            }

        })

        // 创建 CoroutineScope （用于管理CoroutineScope中的所有协程）
        mCoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
        mCoroutineScope.launch(Dispatchers.IO) {
            Log.d("test", "----launch----")
            // TODO 异步线程
            getSharedPreferences("data",Context.MODE_PRIVATE).edit().apply {
                putStringSet("user_info",mutableSetOf("用户名","头像"))
                apply()
            }
            // Splash展示2秒钟
            delay(500)
            // Splash 展示完毕
            mKeepOnAtomicBool.compareAndSet(true, false)
        }

    }

    private fun startSplashScreenExit(splashScreenViewProvider: SplashScreenViewProvider) {
        mCoroutineScope
        splashScreenViewProvider.remove()
        val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
        prefs.edit().let {
            it.putBoolean("isLogin",true)
            it.apply()
        }
        val isLogin = prefs.getBoolean("isLogin",false)
        if (isLogin) MainActivity.actionStart(this)
        else LoginActivity.actionStart(this)
//        MainActivity.actionStart(this)
//        LoginActivity.actionStart(this)
        this.finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        mCoroutineScope.cancel()
    }
}