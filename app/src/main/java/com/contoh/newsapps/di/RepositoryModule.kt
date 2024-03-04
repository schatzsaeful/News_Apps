package com.contoh.newsapps.di

import com.contoh.newsapps.data.repository.NewsRepository
import com.contoh.newsapps.data.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }

}
