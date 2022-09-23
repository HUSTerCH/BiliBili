package com.fengsheng.frontpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.fengsheng.base.adapter.MyFragmentStVpTitleAdapter
import com.fengsheng.frontpage.frontpage.popular.PopularPageFragment
import com.fengsheng.frontpage.frontpage.recommend.RecommendPageFragment
import com.fengsheng.frontpage_export.FrontPageRouter
import kotlinx.android.synthetic.main.fragment_front_page.*
import kotlinx.android.synthetic.main.fragment_recommend_page.*
import kotlinx.coroutines.runBlocking


@Route(path = FrontPageRouter.FRONT_PAGE_ROUTER)
class FrontPageFragment : Fragment() {
    lateinit var fragmentList: ArrayList<Fragment>
    lateinit var titleList: ArrayList<String>
    lateinit var myFragmentStVpTitleAdapter: MyFragmentStVpTitleAdapter
    lateinit var swipeRefresh: SwipeRefreshLayout
    var isRefreshing = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_front_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh = view.findViewById(R.id.front_page_swipe_fresh)
        swipeRefresh.setColorSchemeColors(resources.getColor(R.color.bilibili_pink))
        initData()
        myFragmentStVpTitleAdapter =
            MyFragmentStVpTitleAdapter(childFragmentManager, fragmentList, titleList)
        front_page_viewPager.adapter = myFragmentStVpTitleAdapter
        front_page_tabLayout.setupWithViewPager(front_page_viewPager)

    }

    private fun initData() {
        fragmentList = ArrayList()
        val popularPageFragment = PopularPageFragment()
        val recommendPageFragment = RecommendPageFragment()
        fragmentList.add(recommendPageFragment)
        fragmentList.add(popularPageFragment)

        titleList = ArrayList()
        titleList.add("推荐")
        titleList.add("热门")

        swipeRefresh.setOnChildScrollUpCallback(SwipeRefreshLayout.OnChildScrollUpCallback { _, _ ->
            val recommendVideoList: RecyclerView = recommendPageFragment.recommend_video_list
                ?: return@OnChildScrollUpCallback false
            val layoutManager =
                recommendVideoList.layoutManager as LinearLayoutManager
            layoutManager.findFirstCompletelyVisibleItemPosition() != 0
        })

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = isRefreshing
            runBlocking {
                isRefreshing = recommendPageFragment.refresh()
            }
            swipeRefresh.isRefreshing = isRefreshing

        }
    }

}