package com.fengsheng.videoplay.introduction

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.fengsheng.videoplay.R
import kotlinx.android.synthetic.main.introduction_uploader_info.view.*

class UploaderInfo(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.introduction_uploader_info,this)

        uploader_avatar.setOnClickListener {
            Toast.makeText(context,"大笨蛋",Toast.LENGTH_SHORT).show()
        }
        uploader_basic_info.setOnClickListener {
            Toast.makeText(context,"呆呆呆呆",Toast.LENGTH_SHORT).show()
        }
        follow_unfollow.setOnClickListener {
            Toast.makeText(context,"呆呆 关注了你！",Toast.LENGTH_SHORT).show()
        }

    }


}