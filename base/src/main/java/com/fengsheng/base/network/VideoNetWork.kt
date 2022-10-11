package com.fengsheng.base.network

object VideoNetWork {
    private val videoService = NetWorkManager.create<GetVideo>()
    suspend fun getPopularList() = videoService.getPopular()
    suspend fun getRecommendList() = videoService.getRecommend()
    suspend fun getRelatedVideoListWithBvid(bvid: String) = videoService.getRelatedWithBvid(bvid)
    suspend fun getVideoPlayUrlData(avid: Long, cid: Long) = videoService.getVideoPlayUrl(avid, cid)
    suspend fun getUserCardInfoData(mid: Long) = videoService.getUserCardInfo(mid)
    suspend fun getOnlineNum(bvid: String, cid: Long) = videoService.getOnlineNum(bvid, cid)
}