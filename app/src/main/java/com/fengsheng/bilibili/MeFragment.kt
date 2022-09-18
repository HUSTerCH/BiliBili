package com.fengsheng.bilibili

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fengsheng.bilibili.databinding.FragmentMainBinding
import com.fengsheng.bilibili.databinding.FragmentMeBinding
import com.fengsheng.bilibili.tab.ITabManager
import com.fengsheng.bilibili.tab.TabManager

class MeFragment : BaseFragment<FragmentMeBinding>(), ITabManager by TabManager() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutInflater.inflate(R.layout.fragment_me, null)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMeBinding = FragmentMeBinding.inflate(inflater)
}