package com.fengsheng.base.network

import com.fengsheng.base.network.bean.PopularVideoDataBean
import com.fengsheng.base.network.bean.RecommendVideoDataBean
import com.fengsheng.base.network.bean.RelatedRecommendVideoDataBean
import com.fengsheng.base.network.bean.VideoPlayUrlDataBean
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
}