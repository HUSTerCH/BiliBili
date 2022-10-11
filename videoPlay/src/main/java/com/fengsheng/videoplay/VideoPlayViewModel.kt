package com.fengsheng.videoplay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fengsheng.base.network.VideoNetWork
import com.fengsheng.base.network.bean.VideoPlayUrlDataBean
import kotlinx.coroutines.launch

class VideoPlayViewModel : ViewModel() {
    private val _videoData = MutableLiveData<VideoPlayUrlDataBean>()
    val videoData: LiveData<VideoPlayUrlDataBean> = _videoData

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getVideoPlayUrlData(aid: Long, cid: Long) {
        viewModelScope.launch {
            val result = VideoNetWork.getVideoPlayUrlData(aid,cid)
            _videoData.value = result
        }
    }
}