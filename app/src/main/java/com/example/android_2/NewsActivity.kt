package com.example.android_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import kotlin.jvm.Throws

class NewsActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val mDataset = arrayOf("1", "2")
    var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        mLayoutManager = LinearLayoutManager(this)

        mRecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply{
            setHasFixedSize(true)
            layoutManager = mLayoutManager
        }

        queue = Volley.newRequestQueue(this) // 네트워크 통신을 위해서 큐에 담는다
        getNews()


        //1. 화면이 로딩 -> 뉴스 정보를 받아온다 ------
        //2. 정보 -> 어댑터 넘겨준다
        //3. 어댑터 -> 셋팅
    }


    private fun getNews(){

        val url = "https://newsapi.org/v2/top-headlines?country=kr&category=technology&apiKey=0e0dba402ba343a7af20d980750ca707" // 이 주소로 접속할 것이라고 volley한테 알린다

        // url 주소에 대한 응답으로 string형태가 전달된다.
        val stringRequest = StringRequest(Request.Method.GET, url,
                object : Response.Listener<String> {
                    override fun onResponse(response: String?) { // 뉴스 정보에 대한 string 타입 데이터가 response변수에 들어온다

                        //Log.d("NEWS", response);
                        try {
                            val jsonObj = JSONObject(response) //string을 JSONObject형태로 전환한다
                            val arrayArticles = jsonObj.getJSONArray("articles") //articles라는 키 값을 가니 value값들을 arrayArticles에 저장

                            //response 타입데이터를 -> NewsData Class에 분류
                            val news: MutableList<NewsData> = ArrayList() // 여러 개의 뉴스 기사를 저장할 변수

                            var i = 0
                            val j = arrayArticles.length()
                            while (i < j) {
                                val obj = arrayArticles.getJSONObject(i) // 뉴스 기사 하나를 JSONObject형태로 뽑아온다
                                Log.d("NEWS", obj.toString())
                                val newsData = NewsData() // 한 개의 뉴스기사를 저장할 변수
                                newsData.setTitle(obj.getString("title"))
                                newsData.setUrlToImage(obj.getString("urlToImage"))
                                newsData.setDescription(obj.getString("description"))
                                newsData.setUrl(obj.getString("url"))
                                news.add(newsData)
                                i++
                            }


                            // specify an adapter (see also next example)
                            mAdapter = MyAdapter(
                                    news,
                                    this@NewsActivity,
                                    View.OnClickListener { v ->
                                        val obj = v.tag
                                        if (obj != null) {
                                            val position = obj as Int // position을 가져오기 때문에 Int형으로 형변환
                                            (mAdapter as MyAdapter?)?.getNews(position)?.getDescription()
                                            //그냥 심플하게 빈 화면에 description 정보만 출력하는 방법
                                            val intent = Intent(
                                                    this@NewsActivity,
                                                    MainActivity2::class.java
                                            )
                                            intent.putExtra(
                                                    "news",
                                                    (mAdapter as MyAdapter?)?.getNews(position)
                                            )

                                            startActivity(intent)
                                        }
                                    })
                            mRecyclerView?.adapter = mAdapter
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {} // 에러가 발생했을 때 동작
        })

        // Add the request to the RequestQueue.
        queue?.add(stringRequest)

    }
}