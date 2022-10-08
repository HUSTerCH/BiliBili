package com.fengsheng.videoplay

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fengsheng.base.adapter.MyFragmentStVpTitleAdapter
import com.fengsheng.videoplay.comment.CommentFragment
import com.fengsheng.videoplay.databinding.ActivityVideoPlayBinding
import com.fengsheng.videoplay.introduction.IntroductionFragment
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_video_play.*


class VideoPlayActivity : AppCompatActivity() {
    private lateinit var mFragmentList: ArrayList<Fragment>
    lateinit var mTitleList: ArrayList<String>
    private lateinit var mStateVPAdapter: MyFragmentStVpTitleAdapter
    lateinit var videoExo: SimpleExoPlayer
    private lateinit var viewModel: VideoPlayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
        val avid = intent.getLongExtra("aid", 0)
        val cid = intent.getLongExtra("cid", 0)
        viewModel.getVideoPlayUrlData(avid, cid)
        viewModel.videoData.observe(this) {
            initializePlayer(it.data.durl[0].url)
        }
        mStateVPAdapter = MyFragmentStVpTitleAdapter(
            supportFragmentManager,
            fragmentList = mFragmentList,
            titleList = mTitleList
        )
        video_play_desc_com.adapter = mStateVPAdapter
        top_tabLayout.setupWithViewPager(video_play_desc_com)
    }

    private fun initFragment() {
        viewModel = ViewModelProvider(this)[VideoPlayViewModel::class.java]
        mFragmentList = ArrayList()
        mTitleList = ArrayList()
        val introductionFragment = IntroductionFragment()
        val commentFragment = CommentFragment()
        mFragmentList.add(introductionFragment)
        mFragmentList.add(commentFragment)

        mTitleList.add("简介")
        mTitleList.add("评论")
    }

    private fun initializePlayer(path: String) {
        videoExo = SimpleExoPlayer.Builder(this).build()
        video_play_view.player = videoExo
        val item: MediaItem = MediaItem.fromUri(Uri.parse(path))
        videoExo.addMediaItem(item)
        videoExo.setMediaItem(item)
        videoExo.playWhenReady = true
        videoExo.seekTo(0, 0)
        videoExo.prepare()
    }
}