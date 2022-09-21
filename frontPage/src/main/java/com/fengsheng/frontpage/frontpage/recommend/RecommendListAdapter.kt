package com.fengsheng.frontpage.frontpage.recommend

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fengsheng.base.R

class RecommendListAdapter(private val context: Context,private val videoList:List<RecommendItem>):
    RecyclerView.Adapter<RecommendListAdapter.RecommendListAdapterViewHolder>() {


    inner class RecommendListAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendListAdapterViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.item_video_card_recommend,parent,false)
        return RecommendListAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendListAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(Uri.parse(videoList[position].videoCoverUrl))
            .into(holder.itemView.findViewById(R.id.recommend_video_cover))
        holder.itemView.findViewById<TextView>(R.id.recommend_video_name).text = videoList[position].videoName
        holder.itemView.findViewById<TextView>(R.id.recommend_video_uploader_name).text =
            videoList[position].uploaderName
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}