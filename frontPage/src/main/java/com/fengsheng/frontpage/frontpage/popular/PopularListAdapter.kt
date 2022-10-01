package com.fengsheng.frontpage.frontpage.popular

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fengsheng.base.R
import java.text.SimpleDateFormat
import kotlin.math.floor

class PopularListAdapter(private val context: Context, private val videoList: List<PopularItem>) :
    RecyclerView.Adapter<PopularListAdapter.PopularListAdapterViewHolder>() {
    inner class PopularListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularListAdapterViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_video_card_popular, parent, false)
        return PopularListAdapterViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: PopularListAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(Uri.parse(videoList[position].videoCoverUrl))
            .into(holder.itemView.findViewById(R.id.popular_video_cover))
        holder.itemView.findViewById<TextView>(R.id.popular_video_name).text = videoList[position].videoName
        holder.itemView.findViewById<TextView>(R.id.popular_video_uploader_name).text =
            videoList[position].uploaderName
        holder.itemView.findViewById<TextView>(R.id.popular_video_duration).text =
            setDurationFormat(videoList[position].duration)
        holder.itemView.findViewById<TextView>(R.id.popular_video_watch_times_pubTime).text =
            setWatchTimeAndPubTime(videoList[position].watchTimes, videoList[position].backTime)
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

    private fun setWatchTimeAndPubTime(watchTimesData: Long, pubTimeData: Long): String {
//        观看次数：次数大于1万，显示万，次数大于1亿，显示亿，次数不足一万，显示详细播放量
//        发布时间：距离现在不足24h，显示xx小时前，距离现在超过24h，不足48h，显示"昨天"，距离现在超过48h，显示日期"xx-xx"
        val watchTimeAndPubTime: String
        val pubTime: String

        val watchTime: String = if (watchTimesData > 100000000) {
            String.format("%.1f亿观看", (watchTimesData / 100000000.0))
        } else if (watchTimesData > 10000) {
            String.format("%.1f万次播放", (watchTimesData / 10000.0))
        } else String.format("%.1f次播放", watchTimesData.toDouble())

        val timeStamp = System.currentTimeMillis() / 1000
        val backHours = floor(((timeStamp - pubTimeData) / 3600).toDouble()).toInt()
        pubTime = when (backHours) {
            in 0..23 -> {
                "${backHours}小时前"
            }
            in 24..48 -> {
                "昨天"
            }
            else -> SimpleDateFormat("MM-dd").format(pubTimeData)
        }


        watchTimeAndPubTime = "$watchTime · $pubTime"

        return watchTimeAndPubTime
    }


}