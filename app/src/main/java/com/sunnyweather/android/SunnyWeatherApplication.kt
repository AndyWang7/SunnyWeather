package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "6nushvkX77I15YG3"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}