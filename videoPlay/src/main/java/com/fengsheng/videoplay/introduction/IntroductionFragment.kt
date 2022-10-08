package com.fengsheng.videoplay.introduction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fengsheng.base.network.bean.RelatedRecommendVideoDataBean
import com.fengsheng.videoplay.R
import com.fengsheng.videoplay.introduction.recommend.RecommendItem
import com.fengsheng.videoplay.introduction.recommend.RecommendListAdapter
import com.fengsheng.videoplay.introduction.recommend.RecommendViewModel
import kotlinx.android.synthetic.main.fragment_introduction.*


class IntroductionFragment : Fragment() {

    private var adapter: RecommendListAdapter? = null
    private var viewModel: RecommendViewModel? = null
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
            } else {
                desc?.visibility = View.GONE
                showVideoDesc.setImageResource(com.fengsheng.base.R.drawable.ic_pull_down)
            }
        }
        viewModel = ViewModelProvider(this)[RecommendViewModel::class.java]
        activity?.intent?.getStringExtra("bvid")
            ?.let { viewModel!!.getRelatedVideosDataWithBvid("BV1wa411N7eH") }
        viewModel!!.videosData.observe(viewLifecycleOwner) {
            println(it)
            adapter = context?.let { it1 -> RecommendListAdapter(it1, getVideos(it)) }
            val context = requireActivity().applicationContext
            introduction_recommend.adapter = adapter
            introduction_recommend.layoutManager = LinearLayoutManager(context)
        }

    }



    private fun getVideos(it: RelatedRecommendVideoDataBean): ArrayList<RecommendItem> {
        val videos = ArrayList<RecommendItem>()
        val videoList = it.data
        for (i in videoList.indices) {
            val video = RecommendItem(
                videoList[i].pic,
                videoList[i].title,
                videoList[i].owner.name,
                videoList[i].stat.danmaku,
                videoList[i].duration,
                videoList[i].stat.view,
                videoList[i].pubdate,
                videoList[i].aid,
                videoList[i].cid,
                videoList[i].bvid
            )
            videos.add(video)
        }
        return videos
    }

}