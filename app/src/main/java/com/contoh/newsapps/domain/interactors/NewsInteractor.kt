package com.contoh.newsapps.domain.interactors

import com.contoh.newsapps.domain.models.news.NewsDto

interface NewsInteractor {

    suspend fun getNews(): List<NewsDto>

}
