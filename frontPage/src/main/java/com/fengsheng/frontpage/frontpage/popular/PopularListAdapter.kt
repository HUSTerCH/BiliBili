package com.fengsheng.frontpage.frontpage.popular

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fengsheng.base.R
import java.text.SimpleDateFormat

class PopularListAdapter(private val context: Context, private val videoList: List<PopularItem>) :
    RecyclerView.Adapter<PopularListAdapter.PopularListAdapterViewHolder>() {
    inner class PopularListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularListAdapterViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(com.fengsheng.base.R.layout.item_video_card_popular, parent, false)
        return PopularListAdapterViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: PopularListAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(Uri.parse(videoList[position].videoCoverUrl))
            .into(holder.itemView.findViewById(R.id.video_cover))
//        holder.itemView.findViewById<ImageView>(R.id.video_cover).setImageURI(Uri.parse(videoList[position].videoCoverUrl))
        holder.itemView.findViewById<TextView>(R.id.video_name).text = videoList[position].videoName
        holder.itemView.findViewById<TextView>(R.id.video_uploader_name).text =
            videoList[position].uploaderName
//        剩下的观看次数和发布时间暂时先放下下
        holder.itemView.findViewById<TextView>(R.id.video_duration).text =
            setDurationFormat(videoList[position].duration)
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

    private fun setWatchTimeAndPubTime(): String {
        val watchTimeAndPubTime: String
        val watchTime: Double
        val pubTime: Int

        return watchTimeAndPubTime
    }


}