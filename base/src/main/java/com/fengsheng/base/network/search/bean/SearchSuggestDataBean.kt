package com.fengsheng.base.network.search.bean

import com.google.gson.annotations.SerializedName

data class SearchSuggestDataBean(
    @SerializedName("0")
    val zero: Data,
    @SerializedName("1")
    val one: Data,
    @SerializedName("2")
    val two: Data,
    @SerializedName("3")
    val three: Data,
    @SerializedName("4")
    val four: Data,
    @SerializedName("5")
    val five: Data,
    @SerializedName("6")
    val six: Data,
    @SerializedName("7")
    val seven: Data,
    @SerializedName("8")
    val eight: Data,
    @SerializedName("9")
    val nine: Data,
) {
    data class Data(
        val value: String,
        val term: String,
        val ref: Int,
        val name: String,
        val spid: Int
    )
}
