package com.fengsheng.base.network

import retrofit2.Call
import retrofit2.http.GET

interface GetVideo {
    @GET("popular#")
    suspend fun getPopular(): VideoDataBean
}