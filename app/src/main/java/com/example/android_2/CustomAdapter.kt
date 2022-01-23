package com.example.android_2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2.Models.NewsHealines
import com.squareup.picasso.Picasso

class CustomAdapter(private val context: Context, private val headlines: List<NewsHealines>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.text_title.text = headlines[position].getTitle()
        holder.text_source.text = headlines[position].getSource()!!.getName()
        if (headlines[position].getUrlToImage() != null) {
            Picasso.get().load(headlines[position].getUrlToImage()).into(holder.img_headline)
        }
    }

    override fun getItemCount(): Int {
        return headlines.size
    }

}