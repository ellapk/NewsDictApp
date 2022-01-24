package com.example.android_2

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2.Models.NewsHealines

class Dictionary : AppCompatActivity() {

    lateinit var news: WebView
    lateinit var dictionary: WebView
    var healines: NewsHealines? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        val news = findViewById<WebView>(R.id.news)
        val dictionary = findViewById<WebView>(R.id.dictionary)
        healines = intent.getSerializableExtra("data") as NewsHealines

        dictionary.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        dictionary.loadUrl("https://terms.naver.com/list.naver?cid=41699&categoryId=41699")

        news?.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        news?.loadUrl(healines?.getUrl())


    }

    override fun onBackPressed() {
        if(dictionary.canGoBack()) {
            dictionary.goBack()
        }else {
            super.onBackPressed()
        }
    }





}