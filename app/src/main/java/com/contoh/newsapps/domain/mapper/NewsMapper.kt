package com.contoh.newsapps.domain.mapper

import com.contoh.newsapps.data.network.responses.news.NewsResponse
import com.contoh.newsapps.domain.models.news.NewsDto

class NewsMapper {

    fun transformToNewsDto(
        response: List<NewsResponse?>
    ) = response.map { data ->
        NewsDto(
            source = NewsDto.SourceDto(
                id = data?.source?.id.orEmpty(),
                name = data?.source?.name.orEmpty(),
            ),
            author = data?.author.orEmpty(),
            title = data?.title.orEmpty(),
            description = data?.description.orEmpty(),
            url = data?.url.orEmpty(),
            urlToImage = data?.urlToImage.orEmpty(),
            publishedAt = data?.publishedAt.orEmpty(),
            content = data?.content.orEmpty(),
        )
    }
}
