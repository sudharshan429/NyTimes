package com.sample.nytimes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.nytimes.R
import com.sample.nytimes.model.Result
import kotlinx.android.synthetic.main.mostviewed_item.view.*

class MostViewedAdapter(private val mostViewedList:List<Result>): RecyclerView.Adapter<MostViewedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val item_image = itemView.item_image
        val item_title = itemView.item_title
        val item_by = itemView.item_by
        val item_updated = itemView.item_updated
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.mostviewed_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mostViewedList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.item_title?.text = mostViewedList?.get(position).title
        holder?.item_by?.text = mostViewedList?.get(position).byline
        holder?.item_updated?.text = mostViewedList?.get(position).updated

        Glide.with(holder?.item_image.context)
            .load(mostViewedList?.get(position).media?.get(0)?.media_metadata?.get(0)?.url)
            .apply(RequestOptions.circleCropTransform())
            .into(holder?.item_image)
    }
}