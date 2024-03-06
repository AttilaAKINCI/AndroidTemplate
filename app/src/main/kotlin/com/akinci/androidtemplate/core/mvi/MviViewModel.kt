package com.akinci.androidtemplate.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * MviViewModel is a viewModel wrapper to establish MVI(Model-View-Intent) architecture
 * **/
abstract class MviViewModel<State : Any, Action : Any, Effect : Any>(
    initialState: State,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect by lazy { Channel<Effect>() }
    val effect: Flow<Effect> by lazy { _effect.receiveAsFlow() }

    val currentState: State
        get() = _state.value

    open fun onAction(action: Action) {}

    protected fun updateState(block: State.() -> State) {
        _state.update { block(it) }
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
