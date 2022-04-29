package com.example.alittlemusic

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.alittlemusic.data.logic.model.Banner
import com.example.alittlemusic.data.logic.network.Repository.HomeRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 *
 * @author liuxin
 * Created on 2022/4/10
 * @description：---
 */
@RunWith(MockitoJUnitRunner::class)
class SecurityHelperTest {
    companion object {
          lateinit var context: Context
          lateinit var banner1: LiveData<Result<ArrayList<Banner>>>
          lateinit var banner2: HomeRepository
          lateinit var mSecurityHelper3: HomeRepository
        }
    @Before     //放入公共初始化的内容
    fun init(){
        println("===@Beforeinitcalled===")
        context = mock (Context::class.java)
        banner1 = HomeRepository.getBanner()
    }
    @After      //放入公共销毁的内容
    fun clearDataForTest() {
        println("===@AfterclearDataForTestcalled===")
    }
    @Test
    fun testInstanceOnce(){
        println("ooo@TesttestInstance1calledooo")
    }
}