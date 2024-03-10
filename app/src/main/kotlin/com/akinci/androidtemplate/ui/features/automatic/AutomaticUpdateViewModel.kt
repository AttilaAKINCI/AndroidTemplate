package com.akinci.androidtemplate.ui.features.automatic

import androidx.lifecycle.viewModelScope
import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.core.network.ConnectionManager
import com.akinci.androidtemplate.domain.toDomain
import com.akinci.androidtemplate.ui.features.automatic.AutomaticUpdateViewContract.Action
import com.akinci.androidtemplate.ui.features.automatic.AutomaticUpdateViewContract.Effect
import com.akinci.androidtemplate.ui.features.automatic.AutomaticUpdateViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AutomaticUpdateViewModel @Inject constructor(
    private val connectionManager: ConnectionManager,
) : MviViewModel<State, Action, Effect>(State()) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnGoToManualUpdateButtonClick -> {
                // Log button action, if it's necessary or call another action on close.
                sendEffect(Effect.NavigateManualUpdateScreen)
            }
        }
    }

    init {
        subscribeToNetworkConnectionChanges()
    }

    private fun subscribeToNetworkConnectionChanges() {
        // subscribe to network status changes
        connectionManager.subscribe().onEach { status ->
            updateState { copy(connectionStatus = status.toDomain()) }
        }.launchIn(viewModelScope)
    }
}
