package com.akinci.androidtemplate.ui.features.permission.multiple

import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.core.permission.PermissionManager
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Action
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Effect
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MultiplePermissionRequestViewModel @Inject constructor(
    private val permissionManager: PermissionManager,
) : MviViewModel<State, Action, Effect>(
    State(isPermissionGranted = permissionManager.isCalendarPermissionGranted())
) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnNavigateBackButtonClick -> sendEffect(Effect.NavigateBack)
            Action.OnLifeCycleStart -> verifyPermissions()
            Action.OnCalendarPermissionGranted -> verifyPermissions()
            Action.OnOpenAppSettingsButtonClick -> {
                // dismiss rationale dialog
                updateState { copy(isCalendarPermissionRationaleDialogVisible = false) }
                // navigate to app settings
                sendEffect(Effect.NavigateToAppSettings)
            }

            Action.OnRequestPermissionButtonClick -> {
                if (!permissionManager.isCalendarPermissionGranted()) {
                    // request calendar permission if it's not provided
                    sendEffect(Effect.RequestCalendarPermission)
                }
            }

            Action.OnCalendarPermissionRationaleShow ->
                updateState { copy(isCalendarPermissionRationaleDialogVisible = true) }

            Action.OnCalendarPermissionRationaleDismiss ->
                updateState { copy(isCalendarPermissionRationaleDialogVisible = false) }
        }
    }

    private fun verifyPermissions() {
        updateState { copy(isPermissionGranted = permissionManager.isCalendarPermissionGranted()) }
    }
}
