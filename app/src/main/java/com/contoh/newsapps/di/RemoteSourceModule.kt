package com.contoh.newsapps.di

import android.app.Application
import com.airbnb.mvrx.BuildConfig
import com.contoh.newsapps.data.network.interceptors.ResponseOkInterceptor
import com.contoh.newsapps.data.network.service.NewsService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {

    single {
        Cache(get<Application>().cacheDir, 10 * 1024 * 1024L)
    }

    single {
        val builder = OkHttpClient.Builder()
            .cache(get())
            .followSslRedirects(true)
            .addInterceptor(ResponseOkInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60L, TimeUnit.SECONDS)
            .callTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)

        builder.build()
    }

    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single<NewsService> {
        get<Retrofit>().create(NewsService::class.java)
    }

}
