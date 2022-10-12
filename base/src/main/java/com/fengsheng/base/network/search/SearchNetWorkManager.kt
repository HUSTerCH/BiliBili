package com.fengsheng.base.network.search

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchNetWorkManager {
    private val retrofit1 = Retrofit.Builder()
        .baseUrl("https://s.search.bilibili.com/main/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val retrofit2 = Retrofit.Builder()
        .baseUrl("http://api.bilibili.com/x/web-interface/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun <T> create1(netManager: Class<T>): T = retrofit1.create(netManager)
    fun <T> create2(netManager: Class<T>): T = retrofit2.create(netManager)
    inline fun <reified T> create1(): T = create1(T::class.java)
    inline fun <reified T> create2():T = create2(T::class.java)
}