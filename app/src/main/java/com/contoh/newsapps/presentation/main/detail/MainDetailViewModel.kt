package com.contoh.newsapps.presentation.main.detail

import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.contoh.newsapps.domain.interactors.NewsInteractor
import com.contoh.newsapps.presentation.base.BaseViewModel
import com.contoh.newsapps.utils.ext.scopeInject

class MainDetailViewModel(
    initialState: MainDetailState,
    private val newsInteractor: NewsInteractor
) : BaseViewModel<MainDetailState>(initialState) {

    init {

    }

    companion object : MavericksViewModelFactory<MainDetailViewModel, MainDetailState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: MainDetailState
        ): MainDetailViewModel {
            val newsInteractor: NewsInteractor by viewModelContext.scopeInject()

            return MainDetailViewModel(state, newsInteractor)
        }
    }
}