package com.akinci.androidtemplate.ui.features.manual

import com.akinci.androidtemplate.domain.ConnectionStatus

object ManualUpdateViewContract {

    data class State(
        val connectionStatus: ConnectionStatus = ConnectionStatus.Unknown,
    )

    sealed interface Action {
        data object OnRefreshStatusButtonClick : Action
        data object OnBackButtonClick : Action
    }

    sealed interface Effect {
        data object NavigateBack : Effect
    }
}
