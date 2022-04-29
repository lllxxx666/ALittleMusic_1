package com.example.alittlemusic.data.logic.dao


/**
 *
 * @author liuxin
 * Created on 2022/4/11
 * @description：---
 * LineInfo：歌词行信息：包含行开始时间和歌词行内容
 * LyricInfo：歌词信息：包含标题、歌手、专辑等等
 */
data class LineInfo (var content: String?= "", var startTime: Long?=0)
data class LyricInfo (
    var offset: Long?= 0, var title: String?= "",       //偏移,歌名
    var artist: String?= "", var album: String= "",     //作者，专辑
    var by: String?= "", var lines: ArrayList<LineInfo>)   //制作，歌词