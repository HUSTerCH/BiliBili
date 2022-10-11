package com.fengsheng.bilibili.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.fengsheng.base.adapter.MyFragmentVPAdapter
import com.fengsheng.bilibili.R
import com.fengsheng.bilibili.databinding.ActivityMainBinding
import com.fengsheng.frontpage.FrontPageFragment
import com.fengsheng.me.MeFragment
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = "/main/activity")
class MainActivity : AppCompatActivity() {
    private lateinit var mFragmentList: ArrayList<Fragment>
    private lateinit var mStateVPAdapter: FragmentStatePagerAdapter
    lateinit var spannableString: SpannableString
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottom_navigation.itemIconTintList = null
        bottom_navigation.backgroundTintList = null

        initData()
        setContentView(binding.root)
        mStateVPAdapter = MyFragmentVPAdapter(supportFragmentManager, mFragmentList)
        vp_content.adapter = mStateVPAdapter
        spannableString = SpannableString(bottom_navigation.menu.getItem(0).title)
        spannableString.setSpan(
            ForegroundColorSpan(getColor(R.color.bilibili_pink)),
            0,
            spannableString.length,
            0
        )
        bottom_navigation.menu.getItem(0).title = spannableString
        vp_content.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                onPagerSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_front_page -> {
                    vp_content.currentItem = 0
                    item.setIcon(R.drawable.ic_front_page)
                    spannableString = SpannableString(item.title)
                    spannableString.setSpan(
                        ForegroundColorSpan(getColor(R.color.bilibili_pink)),
                        0,
                        spannableString.length,
                        0
                    )
                    item.title = spannableString

                    val item2 = bottom_navigation.menu.getItem(1)
                        .setIcon(R.drawable.ic_bilibili_line_grey)
                    spannableString = SpannableString(item2.title)
                    spannableString.setSpan(
                        ForegroundColorSpan(getColor(R.color.grey)),
                        0,
                        spannableString.length,
                        0
                    )
                    item2.title = spannableString
                }
                R.id.menu_me -> {
                    vp_content.currentItem = 1
                    item.setIcon(R.drawable.ic_bilibili_line)
                    spannableString = SpannableString(item.title)
                    spannableString.setSpan(
                        ForegroundColorSpan(getColor(R.color.bilibili_pink)),
                        0,
                        spannableString.length,
                        0
                    )
                    item.title = spannableString

                    val item1 =
                        bottom_navigation.menu.getItem(0).setIcon(R.drawable.ic_front_page_grey)
                    spannableString = SpannableString(item1.title)
                    spannableString.setSpan(
                        ForegroundColorSpan(getColor(R.color.grey)),
                        0,
                        spannableString.length,
                        0
                    )
                    item1.title = spannableString
                }
            }
            true
        }

    }

    private fun initData() {
        mFragmentList = ArrayList()
        val frontPageFragment = FrontPageFragment()
        val meFragment = MeFragment()
        mFragmentList.add(frontPageFragment)
        mFragmentList.add(meFragment)
    }


    private fun onPagerSelected(position: Int) {
        when (position) {
            0 -> bottom_navigation.selectedItemId = R.id.menu_front_page
            1 -> bottom_navigation.selectedItemId = R.id.menu_me
        }
    }
}