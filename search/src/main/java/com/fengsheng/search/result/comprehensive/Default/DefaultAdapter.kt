package com.fengsheng.search.result.comprehensive.Default

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fengsheng.base.R
import com.fengsheng.base.ToTime
import com.fengsheng.base.network.search.bean.VideoTypeBean
import kotlin.math.floor

class DefaultAdapter(private val context: Context, private val videoDataBean: VideoTypeBean) :
    RecyclerView.Adapter<DefaultAdapter.DefaultAdapterViewHolder>() {

    inner class DefaultAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultAdapterViewHolder {
        return DefaultAdapterViewHolder(
            LayoutInflater.from(context)
                .inflate(com.fengsheng.base.R.layout.item_video_card_search_rlt, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DefaultAdapterViewHolder, position: Int) {
        val videoList = videoDataBean.data.result
        Glide.with(holder.itemView).load("http:${videoList[position].cover}").into(
            holder.itemView.findViewById(
                R.id.search_rlt_video_cover
            )
        )
        holder.itemView.findViewById<TextView>(R.id.search_rlt_video_name).text =
            videoList[position].title
        holder.itemView.findViewById<TextView>(R.id.search_rlt_video_uploader_name).text =
            videoList[position].author
        holder.itemView.findViewById<TextView>(R.id.search_rlt_video_duration).text =
            videoList[position].duration
        holder.itemView.findViewById<TextView>(R.id.search_rlt_video_watch_times_pubTime).text =
            setWatchTimeAndPubTime(videoList[position].play, videoList[position].pubdate)
    }

    override fun getItemCount(): Int {
        return videoDataBean.data.result.size
    }


    private fun setWatchTimeAndPubTime(watchTimesData: Long, pubTimeData: Long): String {
//        观看次数：次数大于1万，显示万，次数大于1亿，显示亿，次数不足一万，显示详细播放量
//        发布时间：距离现在不足24h，显示xx小时前，距离现在超过24h，不足48h，显示"昨天"，距离现在超过48h，显示日期"xx-xx"
        val watchTimeAndPubTime: String
        val pubTime: String

        val watchTime: String = if (watchTimesData > 100000000) {
            String.format("%.1f亿", (watchTimesData / 100000000.0))
        } else if (watchTimesData > 10000) {
            String.format("%.1f万", (watchTimesData / 10000.0))
        } else String.format("%d", watchTimesData)

        val timeStamp = System.currentTimeMillis() / 1000
        val backHours = floor(((timeStamp - pubTimeData) / 3600).toDouble()).toInt()
        pubTime = when (backHours) {
            in 0..23 -> {
                "${backHours}小时前"
            }
            in 24..48 -> {
                "昨天"
            }
            else -> ToTime.timeStampToStryyyyMMdd(pubTimeData)
        }

        watchTimeAndPubTime = "$watchTime · $pubTime"

        return watchTimeAndPubTime
    }

}