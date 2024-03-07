package com.akinci.androidtemplate.core.application

import android.app.Application
import com.akinci.androidtemplate.core.logger.LoggerInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var loggerInitializer: LoggerInitializer

    override fun onCreate() {
        super.onCreate()

        // initialize timber trees for client side logging
        loggerInitializer.initialize()
    }
}
