package com.fengsheng.search.suggest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fengsheng.search.databinding.ActivitySearchSuggestBinding
import kotlinx.android.synthetic.main.activity_search_suggest.*

class SearchSuggestActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchSuggestViewModel
    private var adapter: SearchSuggestListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchSuggestBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        防止输入法把view顶上去了
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        viewModel = ViewModelProvider(this)[SearchSuggestViewModel::class.java]
        adapter = SearchSuggestListAdapter(this)
        search_suggest_result.layoutManager = LinearLayoutManager(this)
        search_input_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                println("text hasn't changed")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getSuggestData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                println("text has changed")
            }

        })

        viewModel.suggestData.observe(this) {
            if (it.zero != null) {
                adapter!!.setData(it)
                search_suggest_result.adapter = adapter
                search_suggest.visibility = View.VISIBLE
            } else {
                search_suggest.visibility = View.GONE
            }
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }
}