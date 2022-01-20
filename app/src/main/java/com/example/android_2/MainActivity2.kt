package com.example.android_2

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    lateinit var search: EditText
    lateinit var searchButton: Button
    lateinit var dictionary: WebView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        dictionary.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        dictionary.loadUrl("https://www.doopedia.co.kr/index.do")

        search.setOnEditorActionListener {_, actionId , _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                dictionary.loadUrl(search.text.toString())
                true
            } else {
                false
            }
        }

    }

    override fun onBackPressed() {
        if(dictionary.canGoBack()) {
            dictionary.goBack()
        }else {
            super.onBackPressed()
        }
    }





}