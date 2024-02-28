package com.akinci.networkconnectivity.core.application

import android.app.Application
import com.akinci.networkconnectivity.core.logger.LoggerInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NetworkConnectivityApp : Application() {
    @Inject
    lateinit var loggerInitializer: LoggerInitializer

    override fun onCreate() {
        super.onCreate()

        // initialize timber trees
        loggerInitializer.initialize()
    }
}
