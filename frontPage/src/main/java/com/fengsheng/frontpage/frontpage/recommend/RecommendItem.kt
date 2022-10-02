package com.fengsheng.frontpage.frontpage.recommend

data class RecommendItem(
    val videoCoverUrl: String,
    val videoName: String,
    val uploaderName: String,
    val duration: Int,
    val watchTimes: Long,
    val backTime: Long,
    val aid: Long,
    val cid: Long
) {}
