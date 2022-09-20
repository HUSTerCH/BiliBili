package com.fengsheng.base

import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter


object DataBindingAdapters {
    // 根据View的高度和宽高比，设置宽度
    @BindingAdapter("widthHeightRatio")
    fun setWidthHeightRatio(view: View, ratio: Float) {
        view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val height: Int = view.height
                if (height > 0) {
                    view.layoutParams.width = (height * ratio).toInt()
                    view.invalidate()
                    view.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
            }
        })
    }

    //设置高度
    @BindingAdapter("heightWidthRatio")
    fun setHeightWidthRatio(view: View, ratio: Float) {
        view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width: Int = view.width
                if (width > 0) {
                    view.layoutParams.height = (width * ratio).toInt()
                    view.invalidate()
                    view.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
            }

        })
    }

    @InverseBindingAdapter(attribute = "heightWidthRatio", event = "heightWidthRatioAttrChanged")
    fun getHeightWidthRatio(view: View):Float {
        return 0f
    }
}