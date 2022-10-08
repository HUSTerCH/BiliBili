package com.fengsheng.videoplay.introduction

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.fengsheng.videoplay.R
import kotlinx.android.synthetic.main.introduction_video_info.view.*

class VideoInfo(context: Context, attrs: AttributeSet):LinearLayout(context,attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.introduction_video_info,this)
    }
}