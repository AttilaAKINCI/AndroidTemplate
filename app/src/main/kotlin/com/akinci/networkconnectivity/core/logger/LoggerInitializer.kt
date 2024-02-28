package com.akinci.networkconnectivity.core.logger

import com.akinci.networkconnectivity.core.application.AppConfig
import timber.log.Timber
import javax.inject.Inject

class LoggerInitializer @Inject constructor(
    private val appConfig: AppConfig,
) {
    fun initialize() {
        // if we have more logging integrations like firebase crashlytics
        //  we can also bind their timber tree here.

        // bind logging only for debug runs.
        if (appConfig.isDebugMode()) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
