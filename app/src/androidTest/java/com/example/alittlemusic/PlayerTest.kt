package com.example.alittlemusic

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.alittlemusic.ui.player.PlayerActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *
 * @author liuxin
 * Created on 2022/4/11
 * @description：---
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class PlayerTest {

    @get:Rule
    val activityRule = ActivityTestRule(PlayerActivity::class.java)

    @Test
    fun initActivity(){
       onView(withId(R.id.player_title)).perform(click())
        Log.d("test","看看页面")
    }

}