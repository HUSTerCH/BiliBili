package com.fengsheng.search.result.comprehensive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.search.SearchNetWork
import com.fengsheng.base.network.search.bean.VideoTypeBean
import kotlinx.coroutines.launch

class VideoRltViewModel : ViewModel() {
    private val _videosData = MutableLiveData<VideoTypeBean>()
    val videosData: LiveData<VideoTypeBean> = _videosData

    fun getVideosDataSearchRlt(keyword: String, order: String) {
        viewModelScope.launch {
            val result = SearchNetWork.getVideoSearchRlt(keyword, order)
            _videosData.value = result
        }
    }
}