package com.contoh.newsapps.data.repository

import com.contoh.newsapps.data.network.responses.base.BaseResponse
import com.contoh.newsapps.data.network.responses.news.NewsResponse

interface NewsRepository {

    suspend fun getNews(): BaseResponse<NewsResponse>

}
