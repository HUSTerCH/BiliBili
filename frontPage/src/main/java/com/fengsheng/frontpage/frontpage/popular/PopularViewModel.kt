package com.fengsheng.frontpage.frontpage.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.bean.PopularVideoDataBean
import com.fengsheng.base.network.VideoNetWork
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {
    private val _videosData = MutableLiveData<PopularVideoDataBean>()
    val videosData: LiveData<PopularVideoDataBean> = _videosData

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?> = _errorMsg

    fun getPopularVideosData() {
        viewModelScope.launch {
            val result = VideoNetWork.getPopularList()
            _videosData.value = result
        }
    }
}