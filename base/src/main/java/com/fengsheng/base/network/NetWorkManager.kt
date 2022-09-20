package com.fengsheng.base.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWorkManager {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.bilibili.com/x/web-interface/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun <T> create(netManager: Class<T>): T = retrofit.create(netManager)

    inline fun <reified T> create(): T = create(T::class.java)
}