package com.contoh.newsapps.domain.models.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDto(
    val source: SourceDto = SourceDto(),
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = "",
) : Parcelable {

    @Parcelize
    data class SourceDto(
        val id: String = "",
        val name: String = "",
    ) : Parcelable
}