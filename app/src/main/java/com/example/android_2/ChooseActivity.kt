package com.example.android_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ChooseActivity : AppCompatActivity() {

    var goNews: Button? = null
    var goMyVoca: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        goNews = findViewById(R.id.goNews)
        goMyVoca = findViewById(R.id.goMyVoca)

        goNews?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        goMyVoca?.setOnClickListener {
            val intent = Intent(this, VocaReadActivity::class.java)
            startActivity(intent)
        }
    }

}