package com.fengsheng.search.suggest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fengsheng.base.network.search.bean.SearchSuggestDataBean
import com.fengsheng.search.result.SearchResultActivity

class SearchSuggestListAdapter(
    private val context: Context,
) : RecyclerView.Adapter<SearchSuggestListAdapter.SearchSuggestListAdapterViewHolder>() {
    inner class SearchSuggestListAdapterViewHolder(view: View) :
        RecyclerView.ViewHolder(view)

    private var suggestList: ArrayList<SearchSuggestDataBean.Data> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchSuggestListAdapterViewHolder {
        return SearchSuggestListAdapterViewHolder(
            LayoutInflater.from(context)
                .inflate(com.fengsheng.base.R.layout.item_search_suggest, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchSuggestListAdapterViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(com.fengsheng.base.R.id.search_suggest_text).text =
            suggestList[position].name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SearchResultActivity::class.java)
            intent.putExtra("keyword", suggestList[position].name)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return suggestList.size
    }

    fun setData(data: SearchSuggestDataBean) {
        suggestList.clear()
        if (data.zero != null) suggestList.add(data.zero)
        if (data.one != null) suggestList.add(data.one)
        if (data.two != null) suggestList.add(data.two)
        if (data.three != null) suggestList.add(data.three)
        if (data.four != null) suggestList.add(data.four)
        if (data.five != null) suggestList.add(data.five)
        if (data.six != null) suggestList.add(data.six)
        if (data.seven != null) suggestList.add(data.seven)
        if (data.eight != null) suggestList.add(data.eight)
        if (data.nine != null) suggestList.add(data.nine)
    }
}