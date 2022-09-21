package com.fengsheng.base.network

data class RecommendVideoDataBean(val code: Int, val data: List<VideoInfo>, val message: String) {
    data class VideoInfo(
        val aid: Long,
        val bvid: String,
        val cid: Int,
        val copyright: Int,
        val ctime: Int,
        val desc: String,
        val duration: Int,
        val dynamic: String,
        val is_ogv: Boolean,
        val ogv_info: Any,
        val pic: String,
        val pubdate: Long,
        val rcmd_reason: String,
        val season_type: Int,
        val short_link: String,
        val short_link_v2: String,
        val state: Int,
        val tid: Int,
        val title: String,
        val tname: String,
        val videos: Int,
        val rights: Rights,
        val owner: Owner,
        val stat: Stat,
        val dimension: Dimension
    ) {
        data class Rights(
            val arc_pay: Int,
            val autoplay: Int,
            val bp: Int,
            val download: Int,
            val elec: Int,
            val hd5: Int,
            val is_cooperation: Int,
            val movie: Int,
            val no_background: Int,
            val no_reprint: Int,
            val pay: Int,
            val pay_free_watch: Int,
            val ugc_pay: Int,
            val ugc_pay_preview: Int
        )

        data class Owner(
            val face: String,
            val mid: Int,
            val name: String
        )

        data class Stat(
            val aid: Int,
            val coin: Int,
            val danmaku: Int,
            val dislike: Int,
            val favorite: Int,
            val his_rank: Int,
            val like: Int,
            val now_rank: Int,
            val reply: Int,
            val share: Int,
            val view: Long
        )

        data class Dimension(
            val height: Int,
            val rotate: Int,
            val width: Int
        )
    }
}