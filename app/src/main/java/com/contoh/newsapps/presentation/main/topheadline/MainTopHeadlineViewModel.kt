package com.contoh.newsapps.presentation.main.topheadline

import android.util.Log
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.contoh.newsapps.domain.interactors.NewsInteractor
import com.contoh.newsapps.domain.models.news.NewsDto
import com.contoh.newsapps.utils.ext.scopeInject
import com.contoh.newsapps.presentation.base.BaseViewModel

class MainTopHeadlineViewModel(
    initialState: MainTopHeadlineState,
    private val newsInteractor: NewsInteractor
) : BaseViewModel<MainTopHeadlineState>(initialState) {

    fun reset() {
        setState {
            copy(
                newsList = emptyList(),
            )
        }
    }

    fun getNewsList() {
        withState { state ->
            suspend {
                newsInteractor.getNews()
            }.executeOnIo {
                copy(newsListAsync = it)
            }
        }
    }

    fun updateList(leads: List<NewsDto>, totalCount: Int) {
        withState { state ->
            setState {
                copy(
                    newsList = ArrayList(state.newsList).apply {
                        addAll(leads)
                    }
                )
            }
        }
    }

    companion object : MavericksViewModelFactory<MainTopHeadlineViewModel, MainTopHeadlineState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: MainTopHeadlineState
        ): MainTopHeadlineViewModel {
            val newsInteractor: NewsInteractor by viewModelContext.scopeInject()

            return MainTopHeadlineViewModel(state, newsInteractor)
        }
    }
}