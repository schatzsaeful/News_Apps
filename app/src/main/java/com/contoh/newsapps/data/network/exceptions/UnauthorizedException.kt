package com.contoh.newsapps.data.network.exceptions

data class UnauthorizedException(val errorMessage: String?) : Exception(errorMessage)
