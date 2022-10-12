package com.fengsheng.search.result.comprehensive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fengsheng.base.adapter.MyFragmentStVpTitleAdapter
import com.fengsheng.search.R
import com.fengsheng.search.result.comprehensive.Default.ComDefaultFragment
import com.fengsheng.search.result.comprehensive.barragemost.ComBarrageMostFragment
import com.fengsheng.search.result.comprehensive.newest.ComNewestFragment
import com.fengsheng.search.result.comprehensive.viewmost.ComViewMostFragment
import kotlinx.android.synthetic.main.fragment_comprehensive.*

class ComprehensiveFragment : Fragment() {
    lateinit var fragmentList: ArrayList<Fragment>
    lateinit var titleList: ArrayList<String>
    lateinit var myFragmentStVpTitleAdapter: MyFragmentStVpTitleAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comprehensive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        myFragmentStVpTitleAdapter =
            MyFragmentStVpTitleAdapter(childFragmentManager, fragmentList, titleList)
        comprehensive_viewPager.adapter = myFragmentStVpTitleAdapter
        comprehensive_page_tabLayout.setupWithViewPager(comprehensive_viewPager)
    }

    private fun initData() {
        fragmentList = ArrayList()
        val comDefaultFragment = ComDefaultFragment()
        val comNewestFragment = ComNewestFragment()
        val comViewMostFragment = ComViewMostFragment()
        val comBarrageMostFragment = ComBarrageMostFragment()
        fragmentList.add(comDefaultFragment)
        fragmentList.add(comNewestFragment)
        fragmentList.add(comViewMostFragment)
        fragmentList.add(comBarrageMostFragment)
        titleList = ArrayList()
        titleList.add("默认排序")
        titleList.add("新发布")
        titleList.add("播放多")
        titleList.add("弹幕多")

    }

}