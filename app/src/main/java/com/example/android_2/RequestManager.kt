package com.example.android_2

import android.content.Context
import android.widget.Toast
import com.example.android_2.Models.NewsApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RequestManager(var context: Context) { //API 호출을 관리하는 클래스

    //API 호출을 위한 라이브러리를 사용하기 위해 객체를 만든다다
   var retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getNewsHeadlines(listener: OnFetchDataListener<*>, category: String?, query: String?) {
        val callsNewsApi = retrofit.create(CallsNewsApi::class.java)
        val call = callsNewsApi.callHeadlines("kr", category, query, context.getString(R.string.api_key))
        try {
            call.enqueue(object : Callback<NewsApiResponse> {
                override fun onResponse(call: Call<NewsApiResponse>, response: Response<NewsApiResponse>) { //해당 요청에 대한 응답 성공
                    if (!response.isSuccessful) {
                        Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show()
                    }
                    listener.onFetchData(response.body()!!.getArticles(), response.message())
                }

                override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) { //해당 요청에 대한 응답 실패 -> 메세지만 전달
                    listener.onError("Request Failed!!!")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface CallsNewsApi { //API 호출할 때 request parameter로 전달할 수 있는 부분을 작성한다.
        @GET("top-headlines")
        fun callHeadlines(
                @Query("country") country: String?,
                @Query("category") category: String?,
                @Query("q") query: String?,
                @Query("apiKey") api_key: String?
        ): Call<NewsApiResponse>
    }

}