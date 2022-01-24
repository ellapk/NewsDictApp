package com.example.android_2

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2.Models.NewsHealines
import com.google.android.material.appbar.MaterialToolbar

class Dictionary : AppCompatActivity() {

    lateinit var news: WebView
    lateinit var dictionary: WebView
    var healines: NewsHealines? = null
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener { onBackPressed() }
        val news = findViewById<WebView>(R.id.news)
        dictionary = findViewById<WebView>(R.id.dictionary)
        healines = intent.getSerializableExtra("data") as NewsHealines
        dialog = ProgressDialog(this, R.style.AppCompatAlertDialogStyle)

        dictionary.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        dictionary.loadUrl("https://terms.naver.com/list.naver?cid=41699&categoryId=41699")

        news?.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                // 1) 로딩 시작
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    dialogSettings()
                    dialog!!.setMessage("로딩 중입니다.\n" + "잠시만 기다려주십시오.")
                    dialog!!.show()
                }

                // 2) 로딩 끝
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    dialog!!.dismiss()
                }

                // 3) 외부 브라우저가 아닌 웹뷰 자체에서 url 호출
                override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                    view.loadUrl(url)
                    return true
                }

            }
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

    fun dialogSettings(){
        dialog!!.setTitle(R.string.loading)
        dialog!!.setIcon(R.drawable.icon_dialog)
    }

}