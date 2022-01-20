package com.example.android_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ScrollView
import android.widget.SearchView

class MainActivity2 : AppCompatActivity() {

    lateinit var search: SearchView
    lateinit var Dictionary: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


    }


}