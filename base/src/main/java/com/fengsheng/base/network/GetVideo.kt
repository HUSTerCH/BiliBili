package com.fengsheng.base.network

import retrofit2.http.GET
import retrofit2.http.Query

interface GetVideo {
    @GET("popular")
    suspend fun getPopular(): PopularVideoDataBean

    @GET("archive/related")
    suspend fun getRecommend(@Query("aid") aid:Long): RecommendVideoDataBean
}