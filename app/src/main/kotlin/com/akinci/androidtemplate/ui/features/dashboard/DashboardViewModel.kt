package com.akinci.androidtemplate.ui.features.dashboard

import androidx.lifecycle.viewModelScope
import com.akinci.androidtemplate.core.coroutine.ContextProvider
import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Action
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Effect
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val contextProvider: ContextProvider,
) : MviViewModel<State, Action, Effect>(State()) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnCountButtonClick -> {
                // increase counter to update number on the Send toast button
                updateState { copy(counter = counter + 1) }
                // inform ui for new toast message with payload (count)
                sendEffect(Effect.ShowToastMessage(count = currentState.counter))
            }

            Action.OnOpenDetailButtonClick -> {
                // Log button action, if it's necessary or call another action on close.
                sendEffect(Effect.NavigateDetailScreen)
            }
        }
    }

    init {
        // we need to fetch main content of dashboard screen on VM initialization from data source
        // (remote or local) and update ui state accordingly.
        fetchInitialData()
    }

    private fun fetchInitialData() {
        viewModelScope.launch {
            val initialState = withContext(contextProvider.io) {
                // Do IO scope call to fetch screen data
                State(message = "Welcome to Dashboard Screen")
            }

            // update UI state with fetched initial state
            updateState { initialState }
        }
    }
}
