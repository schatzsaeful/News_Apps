package com.contoh.newsapps.domain.interactors

import android.util.Log
import com.contoh.newsapps.data.repository.NewsRepository
import com.contoh.newsapps.domain.mapper.NewsMapper
import com.contoh.newsapps.domain.models.news.NewsDto
import com.contoh.newsapps.utils.ext.mapTo

class NewsInteractorImpl(
    private val repository: NewsRepository,
    private val mapper: NewsMapper
) : NewsInteractor {

    override suspend fun getNews(): List<NewsDto> {
        return repository.getNews().mapTo {
            mapper.transformToNewsDto(it.articles.orEmpty())
        }
    }

}
