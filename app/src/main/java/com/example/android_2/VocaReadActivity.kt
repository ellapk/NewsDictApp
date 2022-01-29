package com.example.android_2

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class VocaReadActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var  layout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_read)

        dbManager = DBManager(this, "vocaDB", null, 1)
        sqlitedb = dbManager.readableDatabase
        layout = findViewById(R.id.vocaList)

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM voca;", null)

        var num : Int = 0
        while (cursor.moveToNext()){
            var str_voca = cursor.getString(cursor.getColumnIndex("voca")).toString()
            var str_mean = cursor.getString(cursor.getColumnIndex("mean")).toString()

            var layout_item : LinearLayout = LinearLayout(this)
            layout_item.orientation=LinearLayout.VERTICAL
            layout_item.setPadding(20,20,20,20)
            layout_item.id= num
            layout_item.setTag(str_voca)

            var tvVoca = TextView(this)
            tvVoca.text = str_voca
            tvVoca.textSize=30F
            tvVoca.setBackgroundColor(Color.rgb(76,170,244))
            tvVoca.setTextColor(Color.WHITE)
            layout_item.addView(tvVoca)

            var tvMean = TextView(this)
            tvMean.text = str_mean
            tvMean.textSize=23F
            tvMean.setBackgroundColor(Color.WHITE)
            tvMean.setTextColor(Color.BLACK)
            layout_item.addView(tvMean)

            layout.addView(layout_item)
            num++
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()
    }
}