package com.fengsheng.bilibili.tab

import androidx.fragment.app.Fragment

data class Tab(
    val tab: ETab,
    val icon: Int?,
    val nameResID: Int?,
//    val fragmentBuilder:() -> Fragment
    val fragment: Fragment
)
