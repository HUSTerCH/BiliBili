package com.fengsheng.base.network.search.bean

data class VideoTypeBean(val code: Int, val message: String, val ttl: Int, val data: Data) {
    data class Data(
        val in_black_key: Int,
        val in_white_key: Int,
        val numPages: Int,
        val numResults: Int,
        val page: Int,
        val pagesize: Int,
        val rqt_type: String,
        val seid: String,
        val show_column: Int,
        val suggest_keyword: String,
        val result: List<Result>
    ) {
        data class Result(
            val aid: Long,
            val arcrank: String,
            val arcurl: String,
            val author: String,
            val badgepay: Boolean,
            val bvid: String,
            val corner: String,
            val cover: String,
            val danmaku: Int,
            val desc: String,
            val description: String,
            val duration: String,
            val favorites: Int,
            val id: Long,
            val is_pay: Int,
            val is_union_video: Int,
            val like: Int,
            val mid: Long,
            val new_rec_tags: List<Any>,
            val pic: String,
            val play: Long,
            val pubdate: Long,
            val rank_score: Int,
            val rec_reason: String,
            val rec_tags: Any,
            val review: Int,
            val senddate: Long,
            val tag: String,
            val title: String,
            val type: String,
            val typeid: String,
            val typename: String,
            val upic: String,
            val url: String,
            val video_review: Int,
            val view_type: String
        )
    }
}