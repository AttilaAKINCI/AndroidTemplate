package com.akinci.androidtemplate.ui.features.detail

object DetailViewContract {

    data class State(
        val message: String = "",
    )

    sealed interface Action {
        data object OnBackButtonClick : Action
    }

    sealed interface Effect {
        data object NavigateBack : Effect
    }
}
