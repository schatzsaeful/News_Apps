package com.contoh.newsapps.data.network.exceptions

data class BadRequestException(val errorMessage: String?) : Exception(errorMessage)
