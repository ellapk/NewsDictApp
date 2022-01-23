package com.example.android_2

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class Dictionary : AppCompatActivity() {

    lateinit var news: WebView
    lateinit var dictionary: WebView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        val news = findViewById<WebView>(R.id.news)
        val dictionary = findViewById<WebView>(R.id.dictionary)

        dictionary.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        dictionary.loadUrl("https://terms.naver.com/list.naver?cid=41699&categoryId=41699")


    }

    override fun onBackPressed() {
        if(dictionary.canGoBack()) {
            dictionary.goBack()
        }else {
            super.onBackPressed()
        }
    }





}