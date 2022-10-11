package com.fengsheng.videoplay.introduction.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.VideoNetWork
import com.fengsheng.base.network.bean.OnlinePeopleDataBean
import com.fengsheng.base.network.bean.RelatedRecommendVideoDataBean
import com.fengsheng.base.network.bean.UserCardInfoDataBean
import kotlinx.coroutines.launch

class IntroductionViewModel : ViewModel() {
    private val _videoData = MutableLiveData<RelatedRecommendVideoDataBean>()
    val videosData: LiveData<RelatedRecommendVideoDataBean> = _videoData

    private val _uploaderData = MutableLiveData<UserCardInfoDataBean>()
    val uploaderData: LiveData<UserCardInfoDataBean> = _uploaderData

    private val _onlineNum = MutableLiveData<OnlinePeopleDataBean>()
    val onlineNum: LiveData<OnlinePeopleDataBean> = _onlineNum

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?> = _errorMsg

    fun getRelatedVideosDataWithBvid(bvid: String) {
        viewModelScope.launch {
            val result = VideoNetWork.getRelatedVideoListWithBvid(bvid)
            _videoData.value = result
        }
    }

    fun getUserCardData(mid: Long) {
        viewModelScope.launch {
            val result = VideoNetWork.getUserCardInfoData(mid)
            _uploaderData.value = result
        }
    }

    fun getOnlinePeopleNum(bvid: String, cid: Long) {
        viewModelScope.launch {
            val result = VideoNetWork.getOnlineNum(bvid, cid)
            _onlineNum.value = result
        }
    }
}