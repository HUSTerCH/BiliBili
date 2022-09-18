package com.fengsheng.bilibili

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.fengsheng.bilibili.main.MainActivity


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = View.inflate(this, R.layout.activity_splash, null)
        setContentView(view)

        val alphaAnimation = AlphaAnimation(1.0f, 1.0f)
        alphaAnimation.duration = 1000
        view.startAnimation(alphaAnimation)
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
    }

    private fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}