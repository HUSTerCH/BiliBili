package com.fengsheng.videoplay.introduction

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.fengsheng.videoplay.R

class Operation(context: Context, attr: AttributeSet?) : LinearLayout(context, attr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.introduction_operation, this)
    }
}