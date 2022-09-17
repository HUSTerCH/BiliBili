package com.fengsheng.me_export

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

interface MeService : IProvider {
    fun getMeFragment():Fragment
}