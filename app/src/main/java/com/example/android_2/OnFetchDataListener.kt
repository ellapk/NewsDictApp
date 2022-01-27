package com.example.android_2

import com.example.android_2.Models.NewsHealines

interface OnFetchDataListener<NewsApiResponse> { //MainActivity에서 작성한 요청에 대한 응답을 다룰 인터페이스를 작성한다
    fun onFetchData(list: List<NewsHealines?>?, message: String?)
    fun onError(message: String?)
}