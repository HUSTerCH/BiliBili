package com.fengsheng.frontpage.frontpage.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fengsheng.frontpage.R
import kotlinx.android.synthetic.main.fragment_popular_page.*

class PopularPageFragment : Fragment() {


    private lateinit var viewModel: PopularViewModel
    var myAdapter: PopularListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_page, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PopularViewModel::class.java]
        viewModel.getPopularVideosData()
        viewModel.videosData.observe(this) {

            myAdapter = context?.let { it1 -> PopularListAdapter(it1, it) }
            val context = requireActivity().applicationContext
            popular_video_list.adapter = myAdapter
            popular_video_list.layoutManager = LinearLayoutManager(context)
        }
    }


}