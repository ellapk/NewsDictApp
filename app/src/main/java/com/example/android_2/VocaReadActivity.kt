package com.example.android_2

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.google.android.material.card.MaterialCardView

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

        var cursor : Cursor = sqlitedb.rawQuery("SELECT * FROM voca;", null)

//        var num : Int = 0
        while (cursor.moveToNext()){
            var str_voca = cursor.getString(cursor.getColumnIndex("voca")).toString()
            var str_mean = cursor.getString(cursor.getColumnIndex("mean")).toString()

            addItemView(str_voca, str_mean)
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()
    }

    private fun addItemView(voca: String, mean: String) {
        val cardView = MaterialCardView(this).apply {
            // 배경색을 설정
            setCardBackgroundColor(Color.rgb(76, 170, 244))

            // 그림자를 설정합니다. 0 = 그림자 표시 x
            cardElevation = 0f
            elevation = 0f
            maxCardElevation = 0f

            // 코너의 둥글기 설정
            radius = dp2Px(22f).toFloat()
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val vocaTextView = TextView(this).apply {
            setTextColor(Color.WHITE)

            // 타이틀 크기 조절
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)

            text = voca
            setPadding(dp2Px(16f))

            //타이틀 한 줄만 표시
            setSingleLine()
        }

        val divider = View(this).apply {
            background = ColorDrawable(Color.WHITE)

            // 투명도를 설정. 1f 은 완전한 불투명을 나타냄
            alpha = 0.8f
        }

        val meanTextView = TextView(this).apply {
            setTextColor(Color.WHITE)
            // setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            text = mean
            setPadding(dp2Px(16f))

            // 최소 라인을 설정
            minLines = 4

            // 최대 라인을 설정
            //maxLines = 4

            // 최대 라인 값을 벗어난 텍스트의 경우 마지막에 ... 을 표시
            //ellipsize = TextUtils.TruncateAt.END
        }

        layout.addView(
                vocaTextView,
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        )

        layout.addView(
                divider,
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2Px(1f)) // dp2Px(1f) 는 디바이더의 두께를 나타냅니다.
        )

        layout.addView(
                meanTextView,
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        )

        cardView.addView(
                layout,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        )

        this.layout.addView(
                cardView,
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    bottomMargin = dp2Px(7f)
                }
        )
    }

    private fun dp2Px(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
}