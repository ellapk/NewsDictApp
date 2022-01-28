package com.example.android_2

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2.Models.NewsHealines
import com.example.android_2.databinding.ActivityDictionaryBinding
import com.example.android_2.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout.*


class Dictionary : AppCompatActivity() {

    lateinit var news: WebView
    lateinit var dictionary: WebView
    var healines: NewsHealines? = null
    var dialog: ProgressDialog? = null
    private lateinit var binding : ActivityDictionaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_dictionary)

        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        val view  = binding.root
        setContentView(view)

        val slidePanel = binding.mainFrame
        slidePanel.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가


        //findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener { onBackPressed() }

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

        binding.btnToggle.setOnClickListener {
            val state = slidePanel.panelState
            if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) { // 닫힌 상태일 경우 열기
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                slidePanel.isTouchEnabled = false
            }
            else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) { // 열린 상태일 경우 닫기
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                slidePanel.isTouchEnabled = true
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.vocabulary, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {

            R.id.voca_read -> { // 나만의 단어장을 볼 수 있도록 액티비티 이동
                val intent = Intent(this,VocaReadActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.voca_add -> { // 단어장에 단어를 추가할 수 있도록 액티비티 이동
                val intent = Intent(this,VocaAddActivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    /*override fun onBackPressed() {
        if(dictionary.canGoBack()) {
            dictionary.goBack()
        }else {
            super.onBackPressed()
        }
    }*/

    fun dialogSettings(){
        dialog!!.setTitle(R.string.loading)
        dialog!!.setCancelable(false)
    }

    inner class PanelEventListener : PanelSlideListener {
        // 패널이 슬라이드 중일 때
        override fun onPanelSlide(panel: View?, slideOffset: Float) {
        }

        // 패널의 상태가 변했을 때
        override fun onPanelStateChanged(panel: View?, previousState: PanelState?, newState: PanelState?) {
            if (newState == PanelState.COLLAPSED) {
                binding.btnToggle.text = "IT 공학 사전 열기"

            } else if (newState == PanelState.EXPANDED) {
                binding.btnToggle.text = "IT 공학 사전 닫기"

            }
        }
    }

}