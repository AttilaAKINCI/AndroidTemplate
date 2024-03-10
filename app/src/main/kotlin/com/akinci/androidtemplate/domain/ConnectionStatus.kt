package com.akinci.androidtemplate.domain

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.akinci.androidtemplate.R

enum class ConnectionStatus(
    val color: Color,
    @StringRes val messageId: Int,
) {
    Connected(color = Color.Green, messageId = R.string.network_connected),
    Disconnected(color = Color.Red, messageId = R.string.network_disconnected),
    Unknown(color = Color.Gray, messageId = R.string.network_unknown),
}
