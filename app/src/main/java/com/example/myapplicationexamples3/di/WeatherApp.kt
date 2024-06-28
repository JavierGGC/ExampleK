package com.example.myapplicationexamples3.di

import android.app.Application
import networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import repositoryModule
import viewModelModule

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}
