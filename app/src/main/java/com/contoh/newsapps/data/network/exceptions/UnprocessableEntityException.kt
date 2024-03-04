package com.contoh.newsapps.data.network.exceptions

data class UnprocessableEntityException(val errorMessage: String?) : Exception(errorMessage)
