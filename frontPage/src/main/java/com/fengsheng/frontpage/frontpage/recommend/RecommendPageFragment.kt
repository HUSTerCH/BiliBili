package com.fengsheng.frontpage.frontpage.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fengsheng.base.network.video.bean.RecommendVideoDataBean
import com.fengsheng.frontpage.R
import kotlinx.android.synthetic.main.fragment_recommend_page.*

class RecommendPageFragment : Fragment() {

    private lateinit var viewModel: RecommendViewModel
    private var myAdapter: RecommendListAdapter? = null
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
            myAdapter = context?.let { it1 -> RecommendListAdapter(it1, it) }
            recommend_video_list.adapter = myAdapter
            recommend_video_list.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }
    }


    fun refresh():Boolean {
        viewModel.getRecommendVideosData()
        return false
    }
}