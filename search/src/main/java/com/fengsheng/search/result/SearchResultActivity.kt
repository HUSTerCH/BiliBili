package com.fengsheng.search.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fengsheng.base.adapter.MyFragmentStVpTitleAdapter
import com.fengsheng.search.databinding.ActivitySearchResultBinding
import com.fengsheng.search.result.column.ColumnFragment
import com.fengsheng.search.result.comprehensive.ComprehensiveFragment
import com.fengsheng.search.result.drama.DramaFragment
import com.fengsheng.search.result.live.LiveFragment
import com.fengsheng.search.result.movie.MovieFragment
import com.fengsheng.search.result.user.UserFragment
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity:AppCompatActivity() {
    lateinit var fragmentList: ArrayList<Fragment>
    lateinit var titleList: ArrayList<String>
    lateinit var myFragmentStVpTitleAdapter: MyFragmentStVpTitleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        myFragmentStVpTitleAdapter = MyFragmentStVpTitleAdapter(supportFragmentManager,fragmentList,titleList)
        search_result_viewPager.adapter = myFragmentStVpTitleAdapter
        search_result_page_tabLayout.setupWithViewPager(search_result_viewPager)
    }
    private fun initData() {
        fragmentList = ArrayList()
        val columnFragment = ColumnFragment()
        val comprehensiveFragment = ComprehensiveFragment()
        val dramaFragment = DramaFragment()
        val liveFragment = LiveFragment()
        val movieFragment = MovieFragment()
        val userFragment = UserFragment()
        fragmentList.add(comprehensiveFragment)
        fragmentList.add(dramaFragment)
        fragmentList.add(liveFragment)
        fragmentList.add(userFragment)
        fragmentList.add(movieFragment)
        fragmentList.add(columnFragment)

        titleList = ArrayList()
        titleList.add("综合")
        titleList.add("番剧")
        titleList.add("直播")
        titleList.add("用户")
        titleList.add("影视")
        titleList.add("专栏")

    }
}