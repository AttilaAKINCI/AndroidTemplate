package com.akinci.androidtemplate.core.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

/**
 * This implementation is used to disable forced splash screen api.
 * **/
@SuppressLint("CustomSplashScreen")
abstract class SplashActivity : ComponentActivity() {

    private var initializationCompleted: Boolean by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { !initializationCompleted }
    }
}
