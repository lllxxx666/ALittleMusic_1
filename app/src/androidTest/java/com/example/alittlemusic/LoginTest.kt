package com.example.alittlemusic

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.alittlemusic.ui.login.LoginActivity
import org.junit.Before
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
class LoginTest {

    private lateinit var mPhone: String
    private lateinit var mPassword: String
    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Before fun initValidString(){
        mPhone = "13780158196"
        mPassword = "XL028803"
    }

    @Test fun changeText_sameActivity(){
        onView(withId(R.id.phone)).perform(typeText(mPhone), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText(mPassword), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
    }
}