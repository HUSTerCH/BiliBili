package com.fengsheng.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView

class VideoCardRecommend(context: Context,attr:AttributeSet):MaterialCardView(context,attr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_video_card_recommend,this)
    }
}