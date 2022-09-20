package com.fengsheng.frontpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fengsheng.frontpage.frontpage.RecommendPageFragment
import com.fengsheng.frontpage.frontpage.popular.PopularPageFragment
import com.fengsheng.frontpage_export.FrontPageRouter
import kotlinx.android.synthetic.main.fragment_front_page.*

@Route(path = FrontPageRouter.FRONT_PAGE_ROUTER)
class FrontPageFragment : Fragment() {
    lateinit var fragmentList: ArrayList<Fragment>
    lateinit var titleList: ArrayList<String>
    lateinit var myFragmentStVpTitleAdapter: MyFragmentStVpTitleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_front_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

}