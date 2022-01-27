package com.example.android_2.Models

import java.io.Serializable

class Source : Serializable {  // json 객체의 source 부분에서 id와 name에 대한 정보를 가지고 있기 때문에 따로 클래스를 생성한다

    private var id = ""
    private var name = "" // name은 기사를 제공하고 있는 업체(Google News 등)명을 나타낸다

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id!!
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name!!
    }

}