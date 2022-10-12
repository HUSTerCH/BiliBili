package com.fengsheng.frontpage.frontpage.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.video.bean.RecommendVideoDataBean
import com.fengsheng.base.network.video.VideoNetWork
import kotlinx.coroutines.launch

class RecommendViewModel : ViewModel() {
    private val _videosData = MutableLiveData<RecommendVideoDataBean>()
    val videosData: LiveData<RecommendVideoDataBean> = _videosData

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?> = _errorMsg
    fun getRecommendVideosData() {
        viewModelScope.launch {
            val result = VideoNetWork.getRecommendList()
            _videosData.value = result
        }
    }
}