package com.fengsheng.frontpage.frontpage.popular

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fengsheng.base.R

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
        holder.itemView.findViewById<ImageView>(R.id.video_cover).setImageURI(Uri.parse(videoList[position].videoCoverUrl))
        holder.itemView.findViewById<TextView>(R.id.video_name).text = videoList[position].videoName
        holder.itemView.findViewById<TextView>(R.id.video_uploader_name).text = videoList[position].uploaderName
//        剩下的观看次数和发布时间暂时先放下下
    }


}