package com.fengsheng.frontpage_export

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

interface FrontPageService : IProvider {

    fun getFrontPageFragment(): Fragment

}