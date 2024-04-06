package com.akinci.androidtemplate.ui.features.dashboard

import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Action
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Effect
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : MviViewModel<State, Action, Effect>(State) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnOpenSinglePermissionScreenButtonClick ->
                sendEffect(Effect.NavigateSinglePermissionScreen)

            Action.OnOpenMultiplePermissionScreenButtonClick ->
                sendEffect(Effect.NavigateMultiplePermissionScreen)
        }
    }
}
