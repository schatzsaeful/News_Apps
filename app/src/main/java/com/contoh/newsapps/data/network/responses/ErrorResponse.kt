package com.contoh.newsapps.data.network.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    val errors: String? = null,
    @SerializedName("service_name")
    val serviceName: Int? = null,
    @SerializedName("code")
    val code: Int? = null
)
