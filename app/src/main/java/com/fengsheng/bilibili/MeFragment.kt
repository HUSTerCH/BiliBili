package com.fengsheng.bilibili

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class MeFragment:Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutInflater.inflate(R.layout.fragment_me,null)
    }
}