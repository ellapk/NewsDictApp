package com.example.android_2

import com.example.android_2.Models.NewsHealines

interface SelectListener {//뉴스 기사 하나에 대한 클릭 리스너 인터페이스
    fun OnNewsClicked(healines: NewsHealines?)
}