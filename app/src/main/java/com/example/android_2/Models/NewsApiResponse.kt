package com.example.android_2.Models

import java.io.Serializable

class NewsApiResponse : Serializable { // 전체 응답에 대한 결과, 기사 상태에 대한 객체를 사용하기 위한 클래스

    private var status: String? = null
    private var totalResults = 0
    private var articles: List<NewsHealines>? = null


    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getTotalResults(): Int {
        return totalResults
    }

    fun setTotalResults(totalResults: Int) {
        this.totalResults = totalResults
    }

    fun getArticles(): List<NewsHealines?>? {
        return articles
    }

    fun setArticles(articles: List<NewsHealines?>?) {
        this.articles = articles as List<NewsHealines>?
    }



}