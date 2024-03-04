package com.contoh.newsapps.data.network.exceptions

data class InternalServerException(val errorMessage: String?) : Exception(errorMessage)
