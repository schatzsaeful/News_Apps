package com.contoh.newsapps.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ResponseOkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return response.newBuilder().code(200).build()
    }
}
