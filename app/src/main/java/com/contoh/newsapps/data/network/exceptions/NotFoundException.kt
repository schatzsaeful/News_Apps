package com.contoh.newsapps.data.network.exceptions

data class NotFoundException(val errorMessage: String?) : Exception(errorMessage)
