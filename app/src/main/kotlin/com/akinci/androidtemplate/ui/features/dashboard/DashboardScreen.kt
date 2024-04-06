package com.akinci.androidtemplate.ui.features.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinci.androidtemplate.R
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.core.mvi.EffectCollector
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Action
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Effect
import com.akinci.androidtemplate.ui.features.destinations.MultiplePermissionRequestScreenDestination
import com.akinci.androidtemplate.ui.features.destinations.SinglePermissionRequestScreenDestination
import com.akinci.androidtemplate.ui.navigation.animations.FadeInOutAnimation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = FadeInOutAnimation::class)
@Composable
fun DashboardScreen(
    navigator: DestinationsNavigator,
    vm: DashboardViewModel = hiltViewModel(),
) {
    // side effects will be handled in EffectCollector block
    EffectCollector(effect = vm.effect) { effect ->
        when (effect) {
            Effect.NavigateSinglePermissionScreen ->
                navigator.navigate(SinglePermissionRequestScreenDestination)

            Effect.NavigateMultiplePermissionScreen ->
                navigator.navigate(MultiplePermissionRequestScreenDestination)
        }
    }

    // Pass uiState to composable content for components as source of data
    DashboardScreenContent(
        onAction = vm::onAction
    )
}

@Composable
private fun DashboardScreenContent(
    onAction: (Action) -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.dashboard_screen_title),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { onAction(Action.OnOpenSinglePermissionScreenButtonClick) }
            ) {
                Text(
                    text = stringResource(id = R.string.dashboard_screen_button_title_single_permission),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { onAction(Action.OnOpenMultiplePermissionScreenButtonClick) }
            ) {
                Text(
                    text = stringResource(id = R.string.dashboard_screen_button_title_multiple_permission),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@UIModePreviews
@Composable
private fun DashboardScreenPreview() {
    AppTheme {
        DashboardScreenContent(
            onAction = {},
        )
    }
}
