package com.akinci.androidtemplate.ui.features.manual

import androidx.lifecycle.viewModelScope
import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.core.network.ConnectionManager
import com.akinci.androidtemplate.domain.toDomain
import com.akinci.androidtemplate.ui.features.manual.ManualUpdateViewContract.Action
import com.akinci.androidtemplate.ui.features.manual.ManualUpdateViewContract.Effect
import com.akinci.androidtemplate.ui.features.manual.ManualUpdateViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManualUpdateViewModel @Inject constructor(
    private val connectionManager: ConnectionManager,
) : MviViewModel<State, Action, Effect>(State()) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnRefreshStatusButtonClick -> updateConnectionStatus()
            Action.OnBackButtonClick -> sendEffect(Effect.NavigateBack)
        }
    }

    init {
        updateConnectionStatus()
    }

    private fun updateConnectionStatus() {
        viewModelScope.launch {
            connectionManager.getStatus()?.toDomain()?.let { status ->
                updateState { copy(connectionStatus = status) }
            }
        }
    }
}
