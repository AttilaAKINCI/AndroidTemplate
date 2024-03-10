package com.akinci.androidtemplate.domain

import com.akinci.androidtemplate.core.network.ConnectionStatus as ConnectionStatusCore

fun ConnectionStatusCore.toDomain() = when (this) {
    ConnectionStatusCore.Connected -> ConnectionStatus.Connected
    ConnectionStatusCore.Disconnected -> ConnectionStatus.Disconnected
    ConnectionStatusCore.Unknown -> ConnectionStatus.Unknown
}
