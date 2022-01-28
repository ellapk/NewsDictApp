package com.example.android_2

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class VocaAddActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var edtVoca: EditText
    lateinit var edtMean: EditText
    lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_add)

        edtVoca = findViewById<EditText>(R.id.edtVoca)
        edtMean = findViewById<EditText>(R.id.edtMean)
        btnAdd = findViewById<Button>(R.id.btnAdd)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        dbManager = DBManager(this, "vocaDB", null, 1)

        btnAdd.setOnClickListener {
            var str_voca: String = edtVoca.text.toString()
            var str_mean: String = edtMean.text.toString()

            if((str_voca.length != 0) && (str_mean.length != 0)){
                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO voca VALUES ('"+str_voca+"', '"+str_mean+"');")
                sqlitedb.close()
                Toast.makeText(this, str_voca + " 입력됨", Toast.LENGTH_SHORT).show()
            }
            else{
                if(str_voca.length == 0){
                    Toast.makeText(this, "단어를 입력하세요", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "뜻을 입력하세요", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}