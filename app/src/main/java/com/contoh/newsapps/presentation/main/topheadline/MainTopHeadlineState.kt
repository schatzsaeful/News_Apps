package com.contoh.newsapps.presentation.main.topheadline

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.contoh.newsapps.domain.models.news.NewsDto

data class MainTopHeadlineState(
    val newsListAsync: Async<List<NewsDto>> = Uninitialized,
    val newsList: List<NewsDto> = emptyList(),
) : MavericksState
