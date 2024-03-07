package com.akinci.androidtemplate.ui.features.dashboard

object DashboardViewContract {

    data class State(
        val message: String = "",
        val counter: Int = 1,
    )

    sealed interface Action {
        data object OnCountButtonClick : Action
        data object OnOpenDetailButtonClick : Action
    }

    sealed interface Effect {
        data object NavigateDetailScreen : Effect
        data class ShowToastMessage(val count: Int) : Effect
    }
}
