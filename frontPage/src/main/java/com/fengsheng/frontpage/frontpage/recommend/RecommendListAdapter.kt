package com.fengsheng.frontpage.frontpage.recommend

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fengsheng.base.R
import com.fengsheng.videoplay.VideoPlayActivity

class RecommendListAdapter(
    private val context: Context,
    private val videoList: List<RecommendItem>
) :
    RecyclerView.Adapter<RecommendListAdapter.RecommendListAdapterViewHolder>() {


    inner class RecommendListAdapterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendListAdapterViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_video_card_recommend, parent, false)
        return RecommendListAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendListAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(Uri.parse(videoList[position].videoCoverUrl))
            .into(holder.itemView.findViewById(R.id.recommend_video_cover))
        holder.itemView.findViewById<ImageView>(R.id.recommend_video_cover)
        holder.itemView.findViewById<TextView>(R.id.recommend_video_name).text =
            videoList[position].videoName
        holder.itemView.findViewById<TextView>(R.id.recommend_video_uploader_name).text =
            videoList[position].uploaderName
        holder.itemView.findViewById<TextView>(R.id.recommend_video_duration).text = setDurationFormat(videoList[position].duration)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,VideoPlayActivity::class.java)
            intent.putExtra("aid",videoList[position].aid)
            intent.putExtra("cid",videoList[position].cid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
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
}