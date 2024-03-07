package com.akinci.androidtemplate.ui.ds.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * AppTheme provides main app theme to composable screens.
 *
 * - Template is designed to use edge to edge content display. Window insets adjustments
 *   are required for each individual composable screen
 * - Dynamic colors are disabled.
 * **/
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}
