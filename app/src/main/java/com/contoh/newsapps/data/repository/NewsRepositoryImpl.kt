package com.contoh.newsapps.data.repository

import com.contoh.newsapps.data.network.service.NewsService
import com.contoh.newsapps.utils.ext.successOrError

class NewsRepositoryImpl(
    private val service: NewsService
) : NewsRepository {

    override suspend fun getNews() = successOrError {
        service.getNews()
    }
}
