package com.akinci.androidtemplate.core.compose

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 *  UIModePreviews is a helper annotation which simplifies Preview implementation of feature screens
 * **/
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class UIModePreviews
