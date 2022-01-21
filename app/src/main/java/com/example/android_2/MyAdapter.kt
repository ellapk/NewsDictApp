package com.example.android_2

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView


class MyAdapter(
        private val mDataset: List<NewsData>?,
        context: Context?,
        private val onClickListener: View.OnClickListener
) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var TextView_title: TextView
        var TextView_content: TextView
        var ImageView_title: SimpleDraweeView
        var rootView: View

        init {
            TextView_title = v.findViewById(R.id.TextView_title)
            TextView_content = v.findViewById(R.id.TextView_content)
            ImageView_title = v.findViewById(R.id.ImageView_title)
            rootView = v
            v.isClickable = true
            v.isEnabled = true
            //v.setOnClickListener(onClickListener)
        }
    }



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_news, parent, false) as LinearLayout
        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(
            holder: MyViewHolder,
            position: Int
    ) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val news = mDataset!![position]
        holder.TextView_title.text = news.getTitle()
        val description = news.getDescription()
        if (description !== "null" && description!!.length > 0) {
            holder.TextView_content.text = description
        } else {
            holder.TextView_content.text = "-"
        }
        val uri = Uri.parse(news.getUrlToImage())
        holder.ImageView_title.setImageURI(uri)

        //tag - label
        holder.rootView.tag = position
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {

        //삼항 연산자
        return mDataset?.size ?: 0
    }

    fun getNews(position: Int): NewsData? {
        return mDataset?.get(position)
    }

    companion object;

    // Provide a suitable constructor (depends on the kind of dataset)
    init {
        Fresco.initialize(context)
    }
}