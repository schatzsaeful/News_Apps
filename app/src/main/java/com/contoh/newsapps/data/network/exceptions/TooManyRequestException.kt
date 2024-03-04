package com.contoh.newsapps.data.network.exceptions

data class TooManyRequestException(val errorMessage: String?) : Exception(errorMessage)
