package com.example.android_2

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class VocaWriteActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var edtVoca: EditText
    lateinit var edtMean: EditText
    lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_read)

        edtVoca = findViewById<EditText>(R.id.edtVoca)
        edtMean = findViewById<EditText>(R.id.edtMean)

        btnAdd = findViewById<Button>(R.id.btnAdd)

        dbManager = DBManager(this,"VocaReadDB", null, 1)

        btnAdd.setOnClickListener {
            var str_name: String = edtVoca.text.toString()
            var str_mean: String = edtMean.text.toString()

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO voca VALUES ('" +str_name+"', '" +str_mean+"')")
            sqlitedb.close()

        }


    }
}