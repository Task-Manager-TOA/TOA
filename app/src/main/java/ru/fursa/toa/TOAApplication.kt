package ru.fursa.toa

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TOAApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}