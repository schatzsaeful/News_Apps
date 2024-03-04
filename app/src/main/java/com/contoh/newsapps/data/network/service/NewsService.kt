package com.contoh.newsapps.data.network.service

import com.contoh.newsapps.data.network.responses.base.BaseResponse
import com.contoh.newsapps.data.network.responses.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") sources: String = "techcrunch",
        @Query("apiKey") apiKey: String = "03859a77d76b4e20ad74e641bdc406d9"
    ): BaseResponse<NewsResponse>
}
