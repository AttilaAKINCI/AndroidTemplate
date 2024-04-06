package com.akinci.androidtemplate.ui.features.dashboard

object DashboardViewContract {

    data object State

    sealed interface Action {
        data object OnOpenSinglePermissionScreenButtonClick : Action
        data object OnOpenMultiplePermissionScreenButtonClick : Action
    }

    sealed interface Effect {
        data object NavigateSinglePermissionScreen : Effect
        data object NavigateMultiplePermissionScreen : Effect
    }
}
