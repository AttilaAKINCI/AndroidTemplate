package com.akinci.androidtemplate.ui.ds.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.akinci.androidtemplate.R
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.akinci.androidtemplate.ui.ds.theme.bodyBold

/**
 *  PermissionRationaleDialog is a dialog for permission rationale
 *
 *  @property [titleRes] is dialog title
 *  @property [descriptionRes] is dialog description
 *  @property [onSettingsClick] is setting button action callback
 *  @property [onDismiss] is dismiss action callback
 *
 * **/
@Composable
fun PermissionRationaleDialog(
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    onSettingsClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Text(
                text = stringResource(id = descriptionRes),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        confirmButton = {
            TextButton(onClick = { onSettingsClick() }) {
                Text(
                    text = stringResource(id = R.string.general_settings),
                    style = MaterialTheme.typography.bodyBold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        },
    )
}

@UIModePreviews
@Composable
fun RationaleDialogPreview() {
    AppTheme {
        Surface(Modifier.fillMaxSize()) {
            PermissionRationaleDialog(
                titleRes = R.string.permission_camera_title,
                descriptionRes = R.string.permission_camera_description,
                onSettingsClick = {},
                onDismiss = {},
            )
        }
    }
}
