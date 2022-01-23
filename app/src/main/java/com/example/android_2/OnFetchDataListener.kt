package com.example.android_2

import com.example.android_2.Models.NewsHealines

interface OnFetchDataListener<NewsApiResponse> {
    fun onFetchData(list: List<NewsHealines?>?, message: String?)
    fun onError(message: String?)
}