package com.akinci.androidtemplate.core.logger

import com.akinci.androidtemplate.core.application.AppConfig
import timber.log.Timber
import javax.inject.Inject

/**
 * LoggerInitializer manages client side logging feature by checking environment parameters.
 * **/
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
