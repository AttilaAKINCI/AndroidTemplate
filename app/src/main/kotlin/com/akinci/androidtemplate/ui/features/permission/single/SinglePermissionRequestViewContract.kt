package com.akinci.androidtemplate.ui.features.permission.single

object SinglePermissionRequestViewContract {

    data class State(
        val isPermissionGranted: Boolean = false,
    )

    sealed interface Action {
        data object OnNavigateBackButtonClick : Action
        data object OnRequestPermissionButtonClick : Action
        data object OnOpenAppSettingsButtonClick : Action
    }

    sealed interface Effect {
        data object NavigateBack : Effect
    }
}
