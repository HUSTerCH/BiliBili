package com.fengsheng.videoplay.introduction.recommend

data class RecommendItem(
    val videoCoverUrl: String,
    val videoName: String,
    val uploaderName: String,
    val barrageNum:Int,
    val duration: Int,
    val watchTimes: Long,
    val backTime: Long,
    val aid: Long,
    val cid: Long,
    val bvid:String
) {}
