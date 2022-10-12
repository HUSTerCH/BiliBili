package com.fengsheng.videoplay.introduction.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.video.VideoNetWork
import com.fengsheng.base.network.video.bean.RelatedRecommendVideoDataBean
import kotlinx.coroutines.launch

class RecommendViewModel : ViewModel() {
    private val _videoData = MutableLiveData<RelatedRecommendVideoDataBean>()
    val videosData: LiveData<RelatedRecommendVideoDataBean> = _videoData

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?> = _errorMsg

    fun getRelatedVideosDataWithBvid(bvid: String) {
        viewModelScope.launch {
            val result = VideoNetWork.getRelatedVideoListWithBvid(bvid)
            _videoData.value = result
        }
    }
}