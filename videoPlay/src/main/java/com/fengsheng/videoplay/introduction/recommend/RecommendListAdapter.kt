package com.fengsheng.videoplay.introduction.recommend

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fengsheng.base.R
import com.fengsheng.base.network.video.bean.RelatedRecommendVideoDataBean
import com.fengsheng.videoplay.VideoPlayActivity

class RecommendListAdapter(
    private val context: Context,
    private val videoData: RelatedRecommendVideoDataBean
) :
    RecyclerView.Adapter<RecommendListAdapter.RecommendListAdapterViewHolder>() {
    inner class RecommendListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendListAdapterViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_card_recommend, parent, false)
        return RecommendListAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoData.data.size
    }

    override fun onBindViewHolder(holder: RecommendListAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(Uri.parse(videoData.data[position].pic))
            .into(holder.itemView.findViewById(R.id.introduction_recommend_video_cover))
        holder.itemView.findViewById<TextView>(R.id.introduction_recommend_video_title).text =
            videoData.data[position].title
        holder.itemView.findViewById<TextView>(R.id.introduction_recommend_watch_time).text =
            setWatchTimeAndPubTime(videoData.data[position].stat.view)
        holder.itemView.findViewById<TextView>(R.id.introduction_recommend_uploader_name).text =
            videoData.data[position].owner.name
        holder.itemView.findViewById<TextView>(R.id.introduction_recommend_duration).text =
            setDurationFormat(videoData.data[position].duration)
        holder.itemView.findViewById<TextView>(R.id.introduction_recommend_barrage_num).text =
            videoData.data[position].stat.danmaku.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, VideoPlayActivity::class.java)
            intent.putExtra("aid", videoData.data[position].aid)
            intent.putExtra("cid", videoData.data[position].cid)
            intent.putExtra("bvid", videoData.data[position].bvid)
            intent.putExtra("watch_times",videoData.data[position].stat.view)
            intent.putExtra("barrage_num",videoData.data[position].stat.danmaku)
            intent.putExtra("video_name",videoData.data[position].title)
            intent.putExtra("uploader_mid",videoData.data[position].owner.mid)
            intent.putExtra("pub_time",videoData.data[position].pubdate)
            intent.putExtra("uploader_mid",videoData.data[position].owner.mid)
            intent.putExtra("like_num",videoData.data[position].stat.like)
            intent.putExtra("coin_num",videoData.data[position].stat.coin)
            intent.putExtra("starry_num",videoData.data[position].stat.favorite)
            intent.putExtra("share_num",videoData.data[position].stat.share)
            intent.putExtra("video_info_detail",videoData.data[position].desc)
            context.startActivity(intent)
        }
    }

    private fun setDurationFormat(duration: Int): String {
        val durationFormat: String
        val hour: Int
        var minute: Int
        val sec: Int
        if (duration >= 3600) {
            hour = duration / 3600
            minute = duration % 3600
            minute /= 60
            sec = minute % 60
            durationFormat = if (minute < 10) {
                if (sec < 10) {
                    "$hour:0$minute:0$sec"
                } else "$hour:0$minute:$sec"
            } else {
                if (sec < 10) {
                    "$hour$minute:0$sec"
                } else "$hour:$minute:$sec"
            }
        } else if (duration < 3600) {
            minute = duration / 60
            sec = duration % 60
            durationFormat = if (sec < 10) {
                "$minute:0$sec"
            } else "$minute:$sec"

        } else durationFormat = "0:00"
        return durationFormat
    }

    private fun setWatchTimeAndPubTime(watchTimesData: Long): String {
//        观看次数：次数大于1万，显示万，次数大于1亿，显示亿，次数不足一万，显示详细播放量
        val watchTimeAndPubTime: String

        val watchTime: String = if (watchTimesData > 100000000) {
            String.format("%.1f亿", (watchTimesData / 100000000.0))
        } else if (watchTimesData > 10000) {
            String.format("%.1f万", (watchTimesData / 10000.0))
        } else String.format("%d", watchTimesData)
        watchTimeAndPubTime = watchTime

        return watchTimeAndPubTime
    }
}