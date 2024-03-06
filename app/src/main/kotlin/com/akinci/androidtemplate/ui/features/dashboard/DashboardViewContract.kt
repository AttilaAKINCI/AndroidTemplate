package com.akinci.androidtemplate.ui.features.dashboard

object DashboardViewContract {

    data class State(
        val message: String = "",
    )

    sealed interface Action {

    }

    sealed interface Effect {

    }
}
