package com.akinci.androidtemplate.ui.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinci.androidtemplate.ui.features.splash.SplashViewContract.Effect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _effect by lazy { Channel<Effect>() }
    val effect: Flow<Effect> by lazy { _effect.receiveAsFlow() }

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch {
            // simulate initialization related tasks here with delay
            delay(3000)

            // complete initialization, proceed next screen
            _effect.send(Effect.Completed)
        }
    }
}
