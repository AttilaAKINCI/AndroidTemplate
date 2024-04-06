package com.akinci.androidtemplate.ui.features.permission.multiple

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akinci.androidtemplate.R
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.core.extensions.getAppSettingsIntent
import com.akinci.androidtemplate.core.mvi.EffectCollector
import com.akinci.androidtemplate.core.permission.Permission
import com.akinci.androidtemplate.ui.ds.components.LifeCycleEventCollector
import com.akinci.androidtemplate.ui.ds.components.PermissionRationaleDialog
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Action
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Effect
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.State
import com.akinci.androidtemplate.ui.navigation.animations.SlideHorizontallyAnimation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPermissionsApi::class)
@Destination(style = SlideHorizontallyAnimation::class)
@Composable
fun MultiplePermissionRequestScreen(
    navigator: DestinationsNavigator,
    vm: MultiplePermissionRequestViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState: State by vm.state.collectAsStateWithLifecycle()

    // state of calendar permission
    val calendarPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Permission.CalendarRead.value,
            Permission.CalenderWrite.value,
        ),
        onPermissionsResult = { permissions ->
            if (permissions.all { it.value }) {
                vm.onAction(Action.OnCalendarPermissionGranted)
            }
        }
    )

    // when we return the screen from settings,
    // this disposable effect automatically triggers permission checks
    LifeCycleEventCollector { event ->
        if (event == Lifecycle.Event.ON_START) {
            vm.onAction(Action.OnLifeCycleStart)
        }
    }

    // side effects will be handled in EffectCollector block
    EffectCollector(effect = vm.effect) { effect ->
        when (effect) {
            Effect.NavigateBack -> navigator.navigateUp()
            Effect.NavigateToAppSettings -> with(context) { startActivity(getAppSettingsIntent()) }
            Effect.RequestCalendarPermission -> {
                if (calendarPermissionState.permissions.any { it.status.shouldShowRationale }) {
                    vm.onAction(Action.OnCalendarPermissionRationaleShow)
                } else {
                    calendarPermissionState.launchMultiplePermissionRequest()
                }
            }
        }
    }

    if (uiState.isCalendarPermissionRationaleDialogVisible) {
        PermissionRationaleDialog(
            titleRes = R.string.permission_calendar_title,
            descriptionRes = R.string.permission_calendar_description,
            onDismiss = { vm.onAction(Action.OnCalendarPermissionRationaleDismiss) },
            onSettingsClick = { vm.onAction(Action.OnOpenAppSettingsButtonClick) },
        )
    }

    MultiplePermissionRequestScreenContent(
        state = uiState,
        onAction = vm::onAction,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MultiplePermissionRequestScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.multiple_permission_request_screen_title),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(Action.OnNavigateBackButtonClick) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                        ),
                        shape = MaterialTheme.shapes.medium,
                    )
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.multiple_permission_request_screen_description),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.multiple_permission_state_title),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Circle,
                    contentDescription = null,
                    tint = if (state.isPermissionGranted) Color.Green else Color.Red,
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                enabled = !state.isPermissionGranted,
                onClick = { onAction(Action.OnRequestPermissionButtonClick) }
            ) {
                Text(
                    text = stringResource(id = R.string.general_request),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                enabled = state.isPermissionGranted,
                onClick = { onAction(Action.OnOpenAppSettingsButtonClick) }
            ) {
                Text(
                    text = stringResource(id = R.string.general_open_app_settings),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@UIModePreviews
@Composable
private fun MultiplePermissionRequestScreen_PermissionGranted_Preview() {
    AppTheme {
        MultiplePermissionRequestScreenContent(
            state = State(isPermissionGranted = true),
            onAction = {},
        )
    }
}

@UIModePreviews
@Composable
private fun MultiplePermissionRequestScreen_PermissionDeclined_Preview() {
    AppTheme {
        MultiplePermissionRequestScreenContent(
            state = State(isPermissionGranted = false),
            onAction = {},
        )
    }
}
