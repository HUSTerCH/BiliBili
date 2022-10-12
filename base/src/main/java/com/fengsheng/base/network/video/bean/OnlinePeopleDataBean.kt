package com.fengsheng.base.network.video.bean

data class OnlinePeopleDataBean(val code: Int, val message: String, val ttl: Int, val data: Data) {
    data class Data(val total: String)
}
