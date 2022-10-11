package com.fengsheng.frontpage.frontpage.recommend

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fengsheng.base.R
import com.fengsheng.base.network.bean.RecommendVideoDataBean
import com.fengsheng.videoplay.VideoPlayActivity

class RecommendListAdapter(
    private val context: Context,
    private val videoData: RecommendVideoDataBean
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
        val videoList = videoData.data.archives
        Glide.with(holder.itemView).load(Uri.parse(videoList[position].pic))
            .into(holder.itemView.findViewById(R.id.recommend_video_cover))
        holder.itemView.findViewById<ImageView>(R.id.recommend_video_cover)
        holder.itemView.findViewById<TextView>(R.id.recommend_video_name).text =
            videoList[position].title
        holder.itemView.findViewById<TextView>(R.id.recommend_video_uploader_name).text =
            videoList[position].owner.name
        holder.itemView.findViewById<TextView>(R.id.recommend_video_duration).text = setDurationFormat(videoList[position].duration)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,VideoPlayActivity::class.java)
            intent.putExtra("aid",videoList[position].aid)
            intent.putExtra("cid",videoList[position].cid)
            intent.putExtra("bvid",videoList[position].bvid)
            intent.putExtra("watch_times",videoList[position].stat.view)
            intent.putExtra("barrage_num",videoList[position].stat.danmaku)
            intent.putExtra("video_name",videoList[position].title)
            intent.putExtra("uploader_mid",videoList[position].owner.mid)
            intent.putExtra("pub_time",videoList[position].pubdate)
            intent.putExtra("uploader_mid",videoList[position].owner.mid)
            intent.putExtra("like_num",videoList[position].stat.like)
            intent.putExtra("coin_num",videoList[position].stat.coin)
            intent.putExtra("starry_num",videoList[position].stat.favorite)
            intent.putExtra("share_num",videoList[position].stat.share)
            intent.putExtra("video_info_detail",videoList[position].desc)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videoData.data.archives.size
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