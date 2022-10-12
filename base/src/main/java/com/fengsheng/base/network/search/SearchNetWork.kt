package com.fengsheng.base.network.search

object SearchNetWork {
    private val searchService1 = SearchNetWorkManager.create1<GetSearchRlt>()

    private val searchService2 = SearchNetWorkManager.create2<GetSearchRlt>()

    suspend fun getSearchSuggest(keyword: String) = searchService1.getSearchSuggest(keyword)

    suspend fun getVideoSearchRlt(keyword: String,order:String) = searchService2.getVideoSearchRlt(keyword, order)


}