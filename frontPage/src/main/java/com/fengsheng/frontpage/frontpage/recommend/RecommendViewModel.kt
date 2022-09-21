package com.fengsheng.frontpage.frontpage.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.PopularVideoDataBean
import com.fengsheng.base.network.RecommendVideoDataBean
import com.fengsheng.base.network.VideoNetWork
import kotlinx.coroutines.launch

class RecommendViewModel : ViewModel() {
    private val _videosData = MutableLiveData<RecommendVideoDataBean>()
    val videosData: LiveData<RecommendVideoDataBean> = _videosData

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?> = _errorMsg
    fun getRecommendVideosData(aid:Long) {
        viewModelScope.launch {
            val result = VideoNetWork.getRecommendList(aid)
            _videosData.value = result
        }
    }
}