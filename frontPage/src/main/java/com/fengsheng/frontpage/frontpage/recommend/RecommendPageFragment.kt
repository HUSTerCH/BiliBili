package com.fengsheng.frontpage.frontpage.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fengsheng.base.network.RecommendVideoDataBean
import com.fengsheng.frontpage.R
import kotlinx.android.synthetic.main.fragment_recommend_page.*

class RecommendPageFragment : Fragment() {

    private var videoList: ArrayList<RecommendItem>? = null
    private lateinit var viewModel: RecommendViewModel
    private var myAdapter: RecommendListAdapter? = null
    var randomVideoAid: Long = 12345678
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommend_page, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[RecommendViewModel::class.java]
        viewModel.getRecommendVideosData()
        viewModel.videosData.observe(this) {
            videoList = getVideos(it)
            myAdapter = context?.let { it1 -> RecommendListAdapter(it1, videoList!!) }
            recommend_video_list.adapter = myAdapter
            recommend_video_list.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun getVideos(it: RecommendVideoDataBean): ArrayList<RecommendItem> {
        val videos = ArrayList<RecommendItem>()
        val videoList = it.data.archives
        for (i in videoList.indices) {
            val video = RecommendItem(
                videoList[i].pic,
                videoList[i].title,
                videoList[i].owner.name,
                videoList[i].duration,
                videoList[i].stat.view,
                videoList[i].pubdate,
                videoList[i].aid,
                videoList[i].cid
            )
            videos.add(video)
        }
        return videos
    }

    fun refresh():Boolean {
        viewModel.getRecommendVideosData()
        return false
    }
}