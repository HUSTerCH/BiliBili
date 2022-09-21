package com.fengsheng.base.network

import retrofit2.http.GET
import retrofit2.http.Query

interface GetVideo {
    @GET("popular")
    suspend fun getPopular(): PopularVideoDataBean

    @GET("dynamic/region?&ps=16&rid=1")
    suspend fun getRecommend(): RecommendVideoDataBean
}