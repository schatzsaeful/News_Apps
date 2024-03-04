package com.contoh.newsapps.presentation.main.everything

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.contoh.newsapps.domain.interactors.NewsInteractor
import com.contoh.newsapps.domain.models.news.NewsDto
import com.contoh.newsapps.utils.ext.scopeInject
import com.contoh.newsapps.presentation.base.BaseViewModel

class MainEverythingViewModel(
    initialState: MainEverythingState,
    private val newsInteractor: NewsInteractor
) : BaseViewModel<MainEverythingState>(initialState) {

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

    companion object : MavericksViewModelFactory<MainEverythingViewModel, MainEverythingState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: MainEverythingState
        ): MainEverythingViewModel {
            val newsInteractor: NewsInteractor by viewModelContext.scopeInject()

            return MainEverythingViewModel(state, newsInteractor)
        }
    }
}