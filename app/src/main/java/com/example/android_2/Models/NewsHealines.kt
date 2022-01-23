package com.example.android_2.Models

class NewsHealines {

    private var source: Source? = null
    private var author = ""
    private var title = ""
    private var description = ""
    private var url = ""
    private var urlToImage = ""
    private var publishedAt = ""
    private var content = ""

    fun getSource(): Source? {
        return source
    }

    fun setSource(source: Source?) {
        this.source = source
    }

    fun getAuthor(): String? {
        return author
    }

    fun setAuthor(author: String?) {
        this.author = author!!
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title!!
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description!!
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url!!
    }

    fun getUrlToImage(): String? {
        return urlToImage
    }

    fun setUrlToImage(urlToImage: String?) {
        this.urlToImage = urlToImage!!
    }

    fun getPublishedAt(): String? {
        return publishedAt
    }

    fun setPublishedAt(publishedAt: String?) {
        this.publishedAt = publishedAt!!
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content!!
    }

}