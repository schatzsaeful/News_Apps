package com.contoh.newsapps.presentation.main.everything

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.contoh.newsapps.domain.models.news.NewsDto

data class MainEverythingState(
    val newsListAsync: Async<List<NewsDto>> = Uninitialized,
    val newsList: List<NewsDto> = emptyList(),
) : MavericksState
