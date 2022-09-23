package com.fengsheng.videoplay

import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fengsheng.base.adapter.MyFragmentStVpTitleAdapter
import com.fengsheng.videoplay.databinding.ActivityVideoPlayBinding
import kotlinx.android.synthetic.main.activity_video_play.*

class VideoPlayActivity : AppCompatActivity() {
    lateinit var mFragmentList: ArrayList<Fragment>
    lateinit var mTitleList: ArrayList<String>
    lateinit var mStateVPAdapter: MyFragmentStVpTitleAdapter
    lateinit var spannableString: SpannableString
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
        mStateVPAdapter = MyFragmentStVpTitleAdapter(
            supportFragmentManager,
            fragmentList = mFragmentList,
            titleList = mTitleList
        )
        video_play_desc_com.adapter = mStateVPAdapter
        top_tabLayout.setupWithViewPager(video_play_desc_com)
//        println("视频播放开始")
//        video_play.start()
    }

    private fun initFragment() {
        mFragmentList = ArrayList()
        mTitleList = ArrayList()
        val introductionFragment = IntroductionFragment()
        val commentFragment = CommentFragment()
        mFragmentList.add(introductionFragment)
        mFragmentList.add(commentFragment)

        mTitleList.add("简介")
        mTitleList.add("评论")
    }
}