package com.example.android_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Thread.sleep(4000) // 4초 기다림
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        startActivity(Intent(this, ChooseActivity::class.java))
        finish()
    }
}