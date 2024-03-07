package com.akinci.androidtemplate.ui.features.splash

object SplashViewContract {
    sealed interface Effect {
        data object Completed : Effect
    }
}
