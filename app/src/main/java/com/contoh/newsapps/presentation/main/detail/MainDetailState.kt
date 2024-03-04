package com.contoh.newsapps.presentation.main.detail

import com.airbnb.mvrx.MavericksState
import com.contoh.newsapps.domain.models.news.NewsDto

data class MainDetailState(
    val news: NewsDto = NewsDto()
) : MavericksState {
    constructor(args: MainDetailArgs) : this(
        news = args.news,
    )
}

