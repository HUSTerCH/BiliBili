package com.fengsheng.videoplay

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.SpannableString
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fengsheng.base.adapter.MyFragmentStVpTitleAdapter
import com.fengsheng.videoplay.databinding.ActivityVideoPlayBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_video_play.*
import okhttp3.internal.userAgent
import java.io.File


class VideoPlayActivity : AppCompatActivity() {
    lateinit var mFragmentList: ArrayList<Fragment>
    lateinit var mTitleList: ArrayList<String>
    lateinit var mStateVPAdapter: MyFragmentStVpTitleAdapter
    lateinit var spannableString: SpannableString
    lateinit var videoExo: SimpleExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
        initializePlayer()
        mStateVPAdapter = MyFragmentStVpTitleAdapter(
            supportFragmentManager,
            fragmentList = mFragmentList,
            titleList = mTitleList
        )
        video_play_desc_com.adapter = mStateVPAdapter
        top_tabLayout.setupWithViewPager(video_play_desc_com)
        Log.e("UA标识", userAgent)
        userAgent
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

    private fun initializePlayer() {
        //全部使用默认设置初始化ExoPlayer
        videoExo = SimpleExoPlayer.Builder(this).build()
        //将显示控件绑定ExoPlayer
        video_play_view.player = videoExo
        val path = Environment.getExternalStorageDirectory().absolutePath + "/test.flv"
        val path1 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        val file = File(path)
        //构建媒体播放的一个Item， 一个item就是一个播放的多媒体文件
        val item: MediaItem = MediaItem.fromUri(Uri.fromFile(file))
        val item1 = MediaItem.fromUri(path1)
//        videoExo.addMediaItem(item)
        videoExo.addMediaItem(item1)
        //设置ExoPlayer需要播放的多媒体item
        videoExo.setMediaItem(item1)
        //设置播放器是否当装备好就播放， 如果看源码可以看出，ExoPlayer的play()方法也是调用的这个方法
        videoExo.playWhenReady = true
        //初始化播放位置
        videoExo.seekTo(0, 0)
        //资源准备，如果设置 setPlayWhenReady(true) 则资源准备好就立马播放。
        videoExo.prepare()
    }

}