package com.example.github_api_app

import android.app.Application
import com.example.github_api_app.di.networkModule
import com.example.github_api_app.di.repositoryModule
import com.example.github_api_app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(viewModelModule, repositoryModule, networkModule)
        }
    }
}