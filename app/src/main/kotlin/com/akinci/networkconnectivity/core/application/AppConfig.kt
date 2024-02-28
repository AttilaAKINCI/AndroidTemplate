package com.akinci.networkconnectivity.core.application

import com.akinci.networkconnectivity.BuildConfig
import javax.inject.Inject

class AppConfig @Inject constructor() {
    fun isDebugMode() = BuildConfig.DEBUG
}
