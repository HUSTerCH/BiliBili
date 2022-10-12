package com.fengsheng.base.network.video.bean
//接口：https://api.bilibili.com/x/web-interface/card?mid=507661630#，实在是太多了，不想一个个加了

data class UserCardInfoDataBean(val code: Int, val message: String, val data: Data) {
    data class Data(
        val archive_count: Int,
        val article_count: Int,
        val card: Card,
        val follower: Int,
        val following: Boolean,
        val like_num: Int
    ) {
        data class Card(
            val DisplayRank: String,
            val approve: Boolean,
            val article: Int,
            val attention: Int,
            val attentions: List<Any>,
            val birthday: String,
            val description: String,
            val face: String,
            val face_nft: Int,
            val face_nft_type: Int,
            val fans: Int,
            val friend: Int,
            val mid: String,
            val name: String,
            val place: String,
            val rank: String,
            val regtime: Int,
            val sex: String,
            val sign: String,
            val spacesta: Int
        )
    }
}
