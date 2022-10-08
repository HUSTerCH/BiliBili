package com.fengsheng.base.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object VideoNetWork {
    private val videoService = NetWorkManager.create<GetVideo>()
    suspend fun getPopularList() = videoService.getPopular()
    suspend fun getRecommendList() = videoService.getRecommend()
    suspend fun getRelatedVideoListWithBvid(bvid:String) = videoService.getRelatedWithBvid(bvid)
    suspend fun getVideoPlayUrlData(avid: Long, cid: Long) = videoService.getVideoPlayUrl(avid, cid)

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body empty")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })

        }
    }
}