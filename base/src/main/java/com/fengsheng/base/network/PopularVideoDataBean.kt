package com.fengsheng.base.network

data class PopularVideoDataBean(val code: Int, val data: Data, val message: String) {
    data class Data(val list: Array<VideoInfo>, val no_more: Boolean) {
        data class VideoInfo(
            val aid: Long,
            val copyright: Int,
            val ctime: Long,
            val desc: String,
            val duration: Int,
            val mission_id: Int,
            val pic: String,
            val pubdate: Long,
            val state: Int,
            val tid: Int,
            val title: String,
            val tname: String,
            val videos: Int,
            val rights: Rights,
            val owner: Owner,
            val stat: Stat,
            val dynamic: String,
            val cid: Long,
            val dimension: Dimension,
            val bvid: String,
            val first_frame: String,
            val is_ogv: Boolean,
            val ogv_info: Any,
            val pub_location: String,
            val season_type: Int,
            val short_link: String,
            val short_link_v2: String,
            val rcmdReason: RcmdReason
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

            data class Dimension(val width: Int, val height: Int, val rotate: Int)
            data class RcmdReason(
                val content: String,
                val corner_mark: Int
            )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Data

            if (!list.contentEquals(other.list)) return false
            if (no_more != other.no_more) return false

            return true
        }

        override fun hashCode(): Int {
            var result = list.contentHashCode()
            result = 31 * result + no_more.hashCode()
            return result
        }
    }
}