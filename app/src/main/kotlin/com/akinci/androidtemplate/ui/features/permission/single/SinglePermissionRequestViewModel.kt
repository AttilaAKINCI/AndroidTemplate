package com.akinci.androidtemplate.ui.features.permission.single

import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.core.permission.Permission
import com.akinci.androidtemplate.core.permission.PermissionManager
import com.akinci.androidtemplate.ui.features.permission.single.SinglePermissionRequestViewContract.Action
import com.akinci.androidtemplate.ui.features.permission.single.SinglePermissionRequestViewContract.Effect
import com.akinci.androidtemplate.ui.features.permission.single.SinglePermissionRequestViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SinglePermissionRequestViewModel @Inject constructor(
    private val permissionManager: PermissionManager,
) : MviViewModel<State, Action, Effect>(
    State(isPermissionGranted = permissionManager.isGranted(Permission.Camera))
) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnNavigateBackButtonClick -> sendEffect(Effect.NavigateBack)
            Action.OnLifeCycleStart -> verifyPermission()
            Action.OnCameraPermissionGranted -> verifyPermission()
            Action.OnOpenAppSettingsButtonClick -> {
                // dismiss rationale dialog
                updateState { copy(isCameraPermissionRationaleDialogVisible = false) }
                // navigate to app settings
                sendEffect(Effect.NavigateToAppSettings)
            }

            Action.OnRequestPermissionButtonClick -> {
                if (!permissionManager.isGranted(Permission.Camera)) {
                    // request camera permission if it's not provided
                    sendEffect(Effect.RequestCameraPermission)
                }
            }

            Action.OnCameraPermissionRationaleShow ->
                updateState { copy(isCameraPermissionRationaleDialogVisible = true) }

            Action.OnCameraPermissionRationaleDismiss ->
                updateState { copy(isCameraPermissionRationaleDialogVisible = false) }
        }
    }

    private fun verifyPermission() {
        updateState { copy(isPermissionGranted = permissionManager.isGranted(Permission.Camera)) }
    }
}
