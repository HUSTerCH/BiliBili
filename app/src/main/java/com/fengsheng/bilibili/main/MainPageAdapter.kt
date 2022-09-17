package com.fengsheng.bilibili

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fengsheng.bilibili.tab.ITabManager

class MainPageAdapter(
    private val tabs: ITabManager,
    private val fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return tabs.getCount()
    }

    override fun createFragment(position: Int): Fragment {
        return tabs.getTab(position)?.fragment ?: Fragment()
    }

    override fun getItemId(position: Int): Long {
        val tab = tabs.getTab(position)
        return tab?.hashCode()?.toLong() ?: 0L
    }

    override fun containsItem(itemId: Long): Boolean {
        val tab = tabs.getTabs().find { it.hashCode().toLong() == itemId }
        return tab != null
    }
}