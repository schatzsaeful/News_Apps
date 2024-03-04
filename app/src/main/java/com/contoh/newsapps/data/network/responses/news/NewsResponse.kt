package com.contoh.newsapps.data.network.responses.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("source")
    val source: SourceResponse?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
) {
    data class SourceResponse(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
    )
}