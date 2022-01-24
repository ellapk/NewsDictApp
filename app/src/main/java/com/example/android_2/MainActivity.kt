package com.example.android_2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2.Models.NewsApiResponse
import com.example.android_2.Models.NewsHealines



class MainActivity : AppCompatActivity(), SelectListener {
    var mRecyclerView: RecyclerView? = null
    var mAdapter: CustomAdapter? = null
    var mLayoutManager : RecyclerView.LayoutManager? = null
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = ProgressDialog(this)
        dialog!!.setTitle("Fetching news articles..") //기사를 가져오는중이라고 로딩에 대한 대화상자 띄우기
        dialog!!.show()
        val manager = RequestManager(this)
        manager.getNewsHeadlines(listener, "technology", null) //여기서 뉴스 기사에 대한 응답을 얻는다

    }

    private val listener: OnFetchDataListener<NewsApiResponse?> = object : OnFetchDataListener<NewsApiResponse?> {
        override fun onFetchData(list: List<NewsHealines?>?, message: String?) {
            showNews(list as List<NewsHealines>) //리사이클러뷰에 뉴스를 표시한 후에 dialog 종료
            dialog?.dismiss()
        }

        override fun onError(message: String?) {}
    }

    private fun showNews(list: List<NewsHealines>) { // 본문을 초기화하는 역할
        mRecyclerView = findViewById(R.id.recycler_main)

        mLayoutManager = GridLayoutManager(this,1)
        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = CustomAdapter(this, list as List<NewsHealines>,this)
        mRecyclerView!!.adapter=mAdapter
        mRecyclerView!!.setHasFixedSize(true)

    }

    override fun OnNewsClicked(healines: NewsHealines?) { //카드뷰를 누르면 다른 액티비티로 넘어가도록 작성한다.

        val intent = Intent(this@MainActivity, Dictionary::class.java)
        intent.putExtra("data", healines) //헤드라인 객체를 webViewActivity에 전달한다.
        startActivity(intent)
    }
}