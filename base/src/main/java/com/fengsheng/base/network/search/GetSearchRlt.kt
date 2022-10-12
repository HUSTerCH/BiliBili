package com.fengsheng.base.network.search

import com.fengsheng.base.network.search.bean.SearchSuggestDataBean
import com.fengsheng.base.network.search.bean.VideoTypeBean
import retrofit2.http.GET
import retrofit2.http.Query

interface GetSearchRlt {
    @GET("suggest?")
    suspend fun getSearchSuggest(@Query("term") term: String): SearchSuggestDataBean

    //获取视频，order为结果排序方式，keyword为关键字
    @GET("search/type?search_type=video")
    suspend fun getVideoSearchRlt(
        @Query("keyword") keyword: String,
        @Query("order") order: String
    ): VideoTypeBean
}