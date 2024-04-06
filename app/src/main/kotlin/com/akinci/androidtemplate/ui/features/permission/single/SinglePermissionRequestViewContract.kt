package com.akinci.androidtemplate.ui.features.permission.single

object SinglePermissionRequestViewContract {

    data class State(
        val isPermissionGranted: Boolean = false,
        val isCameraPermissionRationaleDialogVisible: Boolean = false,
    )

    sealed interface Action {
        data object OnLifeCycleStart : Action
        data object OnNavigateBackButtonClick : Action
        data object OnRequestPermissionButtonClick : Action
        data object OnOpenAppSettingsButtonClick : Action
        data object OnCameraPermissionGranted : Action
        data object OnCameraPermissionRationaleShow: Action
        data object OnCameraPermissionRationaleDismiss: Action
    }

    sealed interface Effect {
        data object NavigateBack : Effect
        data object NavigateToAppSettings: Effect
        data object RequestCameraPermission : Effect
    }
}
