package com.akinci.androidtemplate.ui.features.permission.single

object SinglePermissionRequestViewContract {

    data object State

    sealed interface Action {
        data object OnNavigateBackButtonClick : Action
        data object OnRequestPermissionButtonClick : Action
    }

    sealed interface Effect {
        data object NavigateBack : Effect
    }
}
