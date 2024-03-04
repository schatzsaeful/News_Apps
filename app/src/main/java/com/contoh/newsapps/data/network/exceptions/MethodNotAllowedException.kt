package com.contoh.newsapps.data.network.exceptions

data class MethodNotAllowedException(val errorMessage: String?) : Exception(errorMessage)
