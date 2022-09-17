package com.fengsheng.bilibili.tab

import com.alibaba.android.arouter.launcher.ARouter
import com.fengsheng.bilibili.R
import com.fengsheng.frontpage_export.FrontPageService
import com.fengsheng.me_export.MeService

val frontPageService:FrontPageService = ARouter.getInstance().navigation(FrontPageService::class.java)
val meService:MeService = ARouter.getInstance().navigation(MeService::class.java)

val FRONT_PAGE_TAB = Tab(
    ETab.FRONT_PAGE,
    0,
    R.string.app_tab_frontPage,
    frontPageService.getFrontPageFragment()
)

val ME_TAB = Tab(
    ETab.ME,
    0,
    R.string.app_tab_me,
    meService.getMeFragment()
)

val TabList = listOf(
    FRONT_PAGE_TAB, ME_TAB
)