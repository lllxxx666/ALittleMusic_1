package com.example.alittlemusic

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.alittlemusic.ui.login.LoginActivity
import com.example.alittlemusic.ui.playlist.PlayListActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 *
 * @author liuxin
 * Created on 2022/4/11
 * @description：---
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class PlayListTest {
    @get:Rule
    val activityRule = ActivityTestRule(PlayListActivity::class.java)

    @Before
    fun initDate(){
        activityRule.activity.intent.putExtra("playListId",6952220954)
    }
}