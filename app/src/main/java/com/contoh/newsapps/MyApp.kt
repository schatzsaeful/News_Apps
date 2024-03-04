package com.contoh.newsapps

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import com.contoh.newsapps.di.interactorModule
import com.contoh.newsapps.di.mapperModule
import com.contoh.newsapps.di.remoteDataSourceModule
import com.contoh.newsapps.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeMavericks()
        initializeKoin()
    }

    private fun initializeMavericks() {
        Mavericks.initialize(
            this,
            viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory()
        )
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@MyApp)
            modules(
                remoteDataSourceModule,
                repositoryModule,
                mapperModule,
                interactorModule,
            )
        }
    }
}