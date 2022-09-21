package com.fengsheng.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.item_video_card_popular.view.*


class VideoCardPopular(context: Context,attr:AttributeSet) : MaterialCardView(context,attr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_video_card_popular,this)

    }

}