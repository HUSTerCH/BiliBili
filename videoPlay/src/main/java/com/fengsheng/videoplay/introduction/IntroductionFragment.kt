package com.fengsheng.videoplay.introduction

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fengsheng.base.ToTime
import com.fengsheng.videoplay.R
import com.fengsheng.videoplay.introduction.recommend.IntroductionViewModel
import com.fengsheng.videoplay.introduction.recommend.RecommendListAdapter
import kotlinx.android.synthetic.main.fragment_introduction.*
import kotlinx.android.synthetic.main.introduction_desc.view.*
import kotlinx.android.synthetic.main.introduction_operation.view.*
import kotlinx.android.synthetic.main.introduction_uploader_info.view.*
import kotlinx.android.synthetic.main.introduction_video_info.view.*


class IntroductionFragment : Fragment() {

    private var adapter: RecommendListAdapter? = null
    private var viewModel: IntroductionViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val showVideoDesc = view.findViewById<VideoInfo>(R.id.introduction_video_info)
            ?.findViewById<ImageButton>(R.id.show_video_desc)
        val desc = view.findViewById<Desc>(R.id.introduction_desc)
        showVideoDesc?.setOnClickListener {
            println(showVideoDesc)
            if (desc?.visibility == View.GONE) {
                desc.visibility = View.VISIBLE
                showVideoDesc.setImageResource(com.fengsheng.base.R.drawable.ic_pull_up)
                introduction_video_info.introduction_video_info_video_title.maxLines = 100
            } else {
                desc?.visibility = View.GONE
                introduction_video_info.introduction_video_info_video_title.maxLines = 1
                showVideoDesc.setImageResource(com.fengsheng.base.R.drawable.ic_pull_down)
            }
        }
        viewModel = ViewModelProvider(this)[IntroductionViewModel::class.java]
        activity?.intent?.getStringExtra("bvid")
            ?.let {
                viewModel!!.getRelatedVideosDataWithBvid(it)
                activity?.intent?.getLongExtra("cid", 0)
                    ?.let { it1 -> viewModel!!.getOnlinePeopleNum(it, it1) }
                introduction_desc.introduction_desc_bvid_text.text = it
            }
        activity?.intent?.getLongExtra("uploader_mid", 0)?.let { viewModel!!.getUserCardData(it) }
        viewModel!!.videosData.observe(viewLifecycleOwner) {
            adapter = context?.let { it1 -> RecommendListAdapter(it1, it) }
            val context = requireActivity().applicationContext
            introduction_recommend.adapter = adapter
            introduction_recommend.layoutManager = LinearLayoutManager(context)
        }
        viewModel!!.uploaderData.observe(viewLifecycleOwner) {
            introduction_uploader_info.introduction_uploader_name.text = it.data.card.name
            introduction_uploader_info.introduction_uploader_follow_vnum.text =
                "${it.data.archive_count}视频  ${setFormat(it.data.follower.toLong())}粉丝"
            Glide.with(introduction_uploader_info.introduction_uploader_avatar)
                .load(Uri.parse(it.data.card.face))
                .into(view.findViewById(R.id.introduction_uploader_avatar))
        }
        viewModel!!.onlineNum.observe(viewLifecycleOwner) {
            introduction_video_info.introduction_video_info_online_people_num.text =
                it.data.total + "人正在看"
        }
        initVideoInfo()
    }

    private fun initVideoInfo() {
        val intent = activity?.intent!!
        introduction_video_info.introduction_video_info_video_title.text =
            intent.getStringExtra("video_name")
        introduction_video_info.introduction_video_info_watch_times.text =
            setFormat(intent.getLongExtra("watch_times", 0))
        introduction_video_info.introduction_video_info_barrage_num.text =
            setFormat(intent.getLongExtra("barrage_num", 0))
        introduction_video_info.introduction_video_info_pub_date.text =
            ToTime.timeStampToStrAll(intent.getLongExtra("pub_time", 0))
        introduction_desc.introduction_desc_detail.text = intent.getStringExtra("video_info_detail")
        val likeNum = intent.getIntExtra("like_num", 0)
        introduction_operation.introduction_operation_like_num.text = when (likeNum) {
            0 -> "点赞"
            else -> setFormat(likeNum.toLong())
        }
        val coinNum = intent.getIntExtra("coin_num", 0)
        introduction_operation.introduction_operation_coin_num.text = when (coinNum) {
            0 -> "投币"
            else -> setFormat(coinNum.toLong())
        }
        val starryNum = intent.getIntExtra("starry_num", 0)
        introduction_operation.introduction_operation_star_num.text = when (starryNum) {
            0 -> "收藏"
            else -> setFormat(starryNum.toLong())
        }
        val shareNum = intent.getIntExtra("share_num", 0)
        introduction_operation.introduction_operation_share_num.text = when (shareNum) {
            0 -> "分享"
            else -> setFormat(shareNum.toLong())
        }

    }

    private fun setFormat(data: Long): String {
//        data大于1万，显示万，大于1亿，显示亿，不足一万，显示详细数字
        val formatString: String
        val watchTime: String = if (data > 100000000) {
            String.format("%.1f亿", (data / 100000000.0))
        } else if (data > 10000) {
            String.format("%.1f万", (data / 10000.0))
        } else String.format("%d", data)
        formatString = watchTime

        return formatString
    }

}