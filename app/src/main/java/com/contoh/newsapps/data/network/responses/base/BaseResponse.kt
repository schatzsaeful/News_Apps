package com.contoh.newsapps.data.network.responses.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("articles")
    val articles: List<T>? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("status")
    val status: String? = null
)
