package com.akinci.androidtemplate.ui.features.detail

import androidx.lifecycle.viewModelScope
import com.akinci.androidtemplate.core.coroutine.ContextProvider
import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.Action
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.Effect
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val contextProvider: ContextProvider,
) : MviViewModel<State, Action, Effect>(State()) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnBackButtonClick -> sendEffect(Effect.NavigateBack)
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
                State(message = "Welcome to Detail Screen")
            }

            // update UI state with fetched initial state
            updateState { initialState }
        }
    }
}
