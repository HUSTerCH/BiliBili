package com.fengsheng.bilibili.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fengsheng.base.BaseFragment
import com.fengsheng.bilibili.R
import com.fengsheng.bilibili.databinding.FragmentMainBinding
import com.fengsheng.bilibili.databinding.LayoutMainTabNormalBinding
import com.fengsheng.bilibili.tab.ITabManager
import com.fengsheng.bilibili.tab.TabList
import com.fengsheng.bilibili.tab.TabManager
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BaseFragment<FragmentMainBinding>(), ITabManager by TabManager() {
    private var tabLayoutMediator: TabLayoutMediator? = null
    private lateinit var mainPageAdapter: MainPageAdapter
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun initView() {
        super.initView()
        initTabs()
    }

    private fun initTabs() {
        initTabs(TabList)
        mainPageAdapter = MainPageAdapter(this, this)
        views.vpContent.adapter = mainPageAdapter
        (views.vpContent.getChildAt(0) as? RecyclerView)?.setItemViewCacheSize(getCount())
        tabLayoutMediator =
            TabLayoutMediator(
                views.tlTab,
                views.vpContent,
                true,
                false
            ) { tabLayout, position ->
                tabLayout.setCustomView(R.layout.layout_main_tab_normal)
                tabLayout.customView?.let {
                    val customBinding = LayoutMainTabNormalBinding.bind(it)
                    val tab = getTab(position)
                    customBinding.ivTabIcon.setImageResource(tab?.icon ?: 0)
                    if (tab != null) {
                        tab.nameResID?.let { it1 -> customBinding.tvTabName.setText(it1) }
                    }
                }
            }
    }
}