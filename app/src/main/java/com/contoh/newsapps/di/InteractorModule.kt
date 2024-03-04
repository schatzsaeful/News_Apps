package com.contoh.newsapps.di

import com.contoh.newsapps.domain.interactors.NewsInteractor
import com.contoh.newsapps.domain.interactors.NewsInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<NewsInteractor> {
        NewsInteractorImpl(get(), get())
    }

}
