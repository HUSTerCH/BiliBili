package com.fengsheng.search.suggest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.search.SearchNetWork
import com.fengsheng.base.network.search.bean.SearchSuggestDataBean
import kotlinx.coroutines.launch

class SearchSuggestViewModel : ViewModel() {
    private val _suggestData = MutableLiveData<SearchSuggestDataBean>()
    val suggestData: LiveData<SearchSuggestDataBean> = _suggestData
    fun getSuggestData(keyword: String) {
        viewModelScope.launch {
            val result = SearchNetWork.getSearchSuggest(keyword)
            _suggestData.value = result
        }
    }

}