package com.fengsheng.frontpage.frontpage.popular

import android.net.Uri

data class PopularItem(
    val videoCoverUrl: String,
    val videoName: String,
    val uploaderName: String,
    val duration: Int,
    val watchTimes: Int,
    val backTime: Long
)
