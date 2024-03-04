package com.contoh.newsapps.presentation.main.detail

import android.os.Parcelable
import com.contoh.newsapps.domain.models.news.NewsDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainDetailArgs(
    val news: NewsDto = NewsDto()
) : Parcelable
