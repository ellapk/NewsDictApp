package com.example.android_2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2.Models.NewsHealines
import com.squareup.picasso.Picasso

class CustomAdapter(private val context: Context, headlines: List<NewsHealines>, listener: SelectListener) : RecyclerView.Adapter<CustomViewHolder>() { //리사이클러뷰를 사용하기 위한 어댑터 클래스이다

    private val headlines: List<NewsHealines>
    private val listener: SelectListener



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) { //한 화면에 보여지는 아이템 개수만큼 아이템 레이아웃을 생성하고 뷰홀더 클래스 객체를 생성하는 메소드이다.
        holder.text_title.setText(headlines[position].getTitle())
        holder.text_source.setText(headlines[position].getSource()?.getName())
        if (headlines[position].getUrlToImage() != null) { // 기본 이미지에 대한 설정을 해놓았기 때문에 기사 이미지에 대한 url이 null인지 아닌지 확인하고 NULL이 아니면 뷰홀더에 대한 객체에서 이미지를 설정한다.
            Picasso.get().load(headlines[position].getUrlToImage()).into(holder.img_headline)
        }


        holder.cardView.setOnClickListener { //특정 카드뷰를 누르면 해당되는 카드뷰의 뉴스객체를 인텐트에 담아서 Dictionary.kt로 전달한다.
            listener.OnNewsClicked(headlines[position])
        }

    }

    override fun getItemCount(): Int { //뉴스 목록에 보여줄 아이템의 개수가 리턴된다.
        return headlines.size
    }

    init {
        this.headlines = headlines
        this.listener = listener
    }
}