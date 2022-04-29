package com.example.alittlemusic.util

import android.text.TextUtils
import com.example.alittlemusic.data.logic.dao.LineInfo
import com.example.alittlemusic.data.logic.dao.LyricInfo
import java.io.*


/**
 *
 * @author liuxin
 * Created on 2022/4/11
 * @description：--- 显示歌词所用到的方法
 */

//将时间标签中的内容转为long类型的毫秒值
private fun measureStartTimeMillis(str: String): Long {
    val minute = str.substring(1, 3).toLong()
    val second = str.substring(4, 6).toLong()
    val millisecond = str.substring(7, 9).toLong()
    return millisecond + second * 1000 + minute * 60 * 1000
}

/**
 *
 * 逐行解析歌词

 * @param info 实体类
 *
 * @param line 每行内容
 */
private fun analyzeLyricByLine(info: LyricInfo, line: String?) {
    val index = line!!.lastIndexOf("]")

// 标题
    if (!TextUtils.isEmpty(line) && line.startsWith("[ti:")) {
        info.title = line.substring(4, index).trim { it <= ' ' }
        return
    }

// 歌手
    if (!TextUtils.isEmpty(line) && line.startsWith("[ar:")) {
        info.artist = line.substring(4, index).trim { it <= ' ' }
        return
    }

// 专辑
    if (!TextUtils.isEmpty(line) && line.startsWith("[al:")) {
        info.album = line.substring(4, index).trim { it <= ' ' }
        return
    }

// 制作
    if (!TextUtils.isEmpty(line) && line.startsWith("[by:")) {
        info.by = line.substring(4, index).trim { it <= ' ' }
        return
    }

// 偏移量
    if (!TextUtils.isEmpty(line) && line.startsWith("[offset:")) {
        info.offset = line.substring(8, index).trim { it <= ' ' }.toLong()
        return
    }

// 歌词内容
    if (index == 9 && line.trim { it <= ' ' }.length >= 10) {
        val lineInfo = LineInfo()
        lineInfo.startTime = measureStartTimeMillis(line.substring(0, 10))
        if (line.length == 10) {
            lineInfo.content = ""
        } else {
            lineInfo.content = line.substring(10, line.length)
        }
        info.lines.add(lineInfo) // 添加到歌词集合中
        return
    }
    return
}

/**
 *
 * 解析歌词
 *
 */
fun initLyric(ins: InputStream?, charsetName: String?): LyricInfo? {
    if (ins == null) return null
    try {
        val lyricInfo = LyricInfo(lines = ArrayList())
        val inputStreamReader = InputStreamReader(ins, charsetName)
        val reader = BufferedReader(inputStreamReader)
        var line: String? = null

// 逐行解析
        while (reader.readLine().also { line = it } != null) {
            analyzeLyricByLine(lyricInfo, line)
        }
        reader.close()
        ins.close()
        inputStreamReader.close()
        return lyricInfo
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

//fun check(){
//    try {
//        val `is`: InputStream = getAssets().open("beijing.lrc")
//        val lyricInfo: LyricInfo = LyricParser.initLyric(`is`, "utf-8")
//        val stringBuffer = StringBuffer()
//        if (lyricInfo != null && lyricInfo.lines != null) {
//            val size = lyricInfo.lines.size
//            for (i in 0 until size) {
//                stringBuffer.append(
//                    """
//                    ${lyricInfo.lines[i].content}
//
//                    """.trimIndent()
//                )
//            }
////            tv_lyric.setText(stringBuffer.toString())
//        } else {
//            Log.d("test","解析失败")
//        }
//    } catch (e: IOException) {
//        e.printStackTrace()
//        Log.d("test","解析失败")
//    }
//}