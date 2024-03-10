package com.akinci.androidtemplate.ui.features.automatic

import com.akinci.androidtemplate.domain.ConnectionStatus

object AutomaticUpdateViewContract {

    data class State(
        val connectionStatus: ConnectionStatus = ConnectionStatus.Unknown
    )

    sealed interface Action {
        data object OnGoToManualUpdateButtonClick : Action
    }

    sealed interface Effect {
        data object NavigateManualUpdateScreen : Effect
    }
}
