package com.fengsheng.bilibili.tab

interface ITabManager {
    fun initTabs(tabs:List<Tab>)
    fun getCount():Int
    fun getTab(index:Int):Tab?
    fun getTabs():List<Tab>
}
class TabManager :ITabManager{
    private val tabs = mutableListOf<Tab>()
    override fun initTabs(tabs: List<Tab>) {
        this.tabs.clear()
        this.tabs.addAll(tabs)
    }

    override fun getCount(): Int {
        return this.tabs.size
    }

    override fun getTab(index: Int): Tab? {
        return if (index in tabs.indices) tabs[index] else null
    }

    override fun getTabs(): List<Tab> = tabs

}