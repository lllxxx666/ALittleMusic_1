package com.example.alittlemusic.util

import android.content.Context
import android.widget.Toast
import com.example.alittlemusic.baseClass.MyApplication
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author liuxin
 * Created on 2022/4/17
 * @description：---简化Toast函数
 */
fun toast(message: String){
    Toast.makeText(MyApplication.context,message,Toast.LENGTH_SHORT).show()
}

/**
 *
 * @author liuxin
 * Created on 2022/4/17
 * @description：---返回当前日期，格式：2022-4-17 12:13:55
 *
 */
fun getNowY(): String {
    if (android.os.Build.VERSION.SDK_INT >= 24){
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
    }else{
        var tms = Calendar.getInstance()
        return tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE).toString() +":" + tms.get(Calendar.SECOND).toString() +"." + tms.get(Calendar.MILLISECOND).toString()
    }
}
fun getNowM(): String {
    if (android.os.Build.VERSION.SDK_INT >= 24){
        return SimpleDateFormat("MM-dd").format(Date())
    }else{
        var tms = Calendar.getInstance()
        return tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString()
    }
}