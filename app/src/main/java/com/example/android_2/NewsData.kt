package com.example.android_2

import java.io.Serializable

class NewsData : Serializable { // 어댑터에  JSONObject타입의 데이터에서 필요한 값만 뽑아서 전달하기 위해 클래스 생성

    private var title: String? = null
    private var urlToImage: String? = null
    private var description: String? = null
    private var url : String? = null

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getUrlToImage(): String? {
        return urlToImage
    }

    fun getUrl(): String?{
        return url
    }

    fun setUrlToImage(urlToImage: String?) {
        this.urlToImage = urlToImage
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }
    fun setUrl(url : String?){
        this.url = url
    }

}