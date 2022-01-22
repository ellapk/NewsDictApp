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
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

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
        //adapter = MyAdapter(myDataSet)

        mRecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply{
            setHasFixedSize(true)
            layoutManager = mLayoutManager
        }

        queue = Volley.newRequestQueue(this)
        getNews()

        //1. 화면이 로딩 -> 뉴스 정보를 받아온다 ------
        //2. 정보 -> 어댑터 넘겨준다
        //3. 어댑터 -> 셋팅
    }//그냥 심플하게 빈 화면에 description 정보만 출력하는 방법


    private fun getNews(){
        val url = "https://newsapi.org/v2/top-headlines?country=kr&category=technology&apiKey=0e0dba402ba343a7af20d980750ca707"
        //String url ="http://newsapi.org/v2/top-headlines?country=kr&apiKey=";

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
                object : Response.Listener<String> {
                    override fun onResponse(response: String?) {

                        //Log.d("NEWS", response);
                        try {
                            val jsonObj = JSONObject(response)
                            val arrayArticles = jsonObj.getJSONArray("articles")

                            //response ->> NewsData Class 분류
                            val news: MutableList<NewsData> =
                                    ArrayList()

                            var i = 0
                            val j = arrayArticles.length()
                            while (i < j) {
                                val obj = arrayArticles.getJSONObject(i)
                                Log.d("NEWS", obj.toString())
                                val newsData = NewsData()
                                newsData.setTitle(obj.getString("title"))
                                newsData.setUrlToImage(obj.getString("urlToImage"))
                                newsData.setDescription(obj.getString("description"))
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
                                            val position = obj as Int
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
            override fun onErrorResponse(error: VolleyError?) {}
        })

        // Add the request to the RequestQueue.
        queue?.add(stringRequest)

    }
}