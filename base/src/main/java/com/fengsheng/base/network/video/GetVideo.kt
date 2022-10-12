package com.fengsheng.base.network.video


import com.fengsheng.base.network.video.bean.*
import retrofit2.http.GET
import retrofit2.http.Query

interface GetVideo {
    @GET("web-interface/popular")
    suspend fun getPopular(): PopularVideoDataBean

    @GET("web-interface/dynamic/region?&ps=16&rid=1")
    suspend fun getRecommend(): RecommendVideoDataBean

    @GET("web-interface/archive/related?")
    suspend fun getRelatedWithBvid(@Query("bvid") bvid: String): RelatedRecommendVideoDataBean

    @GET("player/playurl?")
    suspend fun getVideoPlayUrl(
        @Query("avid") avid: Long,
        @Query("cid") cid: Long
    ): VideoPlayUrlDataBean

    @GET("web-interface/card?")
    suspend fun getUserCardInfo(
        @Query("mid") mid: Long
    ): UserCardInfoDataBean

    @GET("player/online/total?")
    suspend fun getOnlineNum(
        @Query("bvid") bvid: String,
        @Query("cid") cid: Long
    ): OnlinePeopleDataBean
}