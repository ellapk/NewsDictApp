package com.example.android_2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //리사이클러뷰에 대한 뷰홀더 클래스이다. 목록에 보여줄 아이템 레이아웃에 값을 입력한다
    var text_title: TextView
    var text_source: TextView
    var img_headline: ImageView
    var cardView: CardView

    init {
        text_title = itemView.findViewById(R.id.text_title)
        text_source = itemView.findViewById(R.id.text_source)
        img_headline = itemView.findViewById(R.id.img_headline)
        cardView = itemView.findViewById(R.id.main_container)
    }
}