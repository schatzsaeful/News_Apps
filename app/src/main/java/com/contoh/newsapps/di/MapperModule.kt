package com.contoh.newsapps.di

import com.contoh.newsapps.domain.mapper.NewsMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        NewsMapper()
    }

}
