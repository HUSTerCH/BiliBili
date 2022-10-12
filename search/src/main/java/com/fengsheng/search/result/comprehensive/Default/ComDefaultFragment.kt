package com.fengsheng.search.result.comprehensive.Default

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fengsheng.search.R
import com.fengsheng.search.result.comprehensive.VideoRltViewModel
import kotlinx.android.synthetic.main.comprehensive_default.*

class ComDefaultFragment : Fragment() {
    private lateinit var viewModel: VideoRltViewModel
    private var myAdapter: DefaultAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comprehensive_default, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[VideoRltViewModel::class.java]
        activity?.intent?.getStringExtra("keyword").let {
            viewModel.getVideosDataSearchRlt(it!!, "totalrank")
        }
        viewModel.videosData.observe(viewLifecycleOwner) {
            myAdapter = context?.let { it1 ->
                viewModel.videosData.value?.let { it2 ->
                    DefaultAdapter(
                        it1,
                        it2
                    )
                }
            }
            com_default_recycler.adapter = myAdapter
            com_default_recycler.layoutManager = LinearLayoutManager(context)
        }

    }

}