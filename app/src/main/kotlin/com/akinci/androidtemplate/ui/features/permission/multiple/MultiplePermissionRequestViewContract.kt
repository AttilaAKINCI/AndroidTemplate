package com.akinci.androidtemplate.ui.features.permission.multiple

object MultiplePermissionRequestViewContract {

    data class State(
        val isPermissionGranted: Boolean = false,
        val isCalendarPermissionRationaleDialogVisible: Boolean = false,
    )

    sealed interface Action {
        data object OnLifeCycleStart : Action
        data object OnNavigateBackButtonClick : Action
        data object OnRequestPermissionButtonClick : Action
        data object OnOpenAppSettingsButtonClick : Action
        data object OnCalendarPermissionGranted : Action
        data object OnCalendarPermissionRationaleShow: Action
        data object OnCalendarPermissionRationaleDismiss: Action
    }

    sealed interface Effect {
        data object NavigateBack : Effect
        data object NavigateToAppSettings: Effect
        data object RequestCalendarPermission : Effect
    }
}
