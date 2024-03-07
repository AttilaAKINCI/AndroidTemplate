package com.akinci.androidtemplate.ui.features.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akinci.androidtemplate.R
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.core.mvi.EffectCollector
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Action
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Effect
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.State
import com.akinci.androidtemplate.ui.features.destinations.DetailScreenDestination
import com.akinci.androidtemplate.ui.navigation.animations.FadeInOutAnimation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = FadeInOutAnimation::class)
@Composable
fun DashboardScreen(
    navigator: DestinationsNavigator,
    vm: DashboardViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    // lifecycle collection for ui state
    val uiState: State by vm.state.collectAsStateWithLifecycle()

    // side effects will be handled in EffectCollector block
    EffectCollector(effect = vm.effect) { effect ->
        when (effect) {
            Effect.NavigateDetailScreen -> navigator.navigate(DetailScreenDestination)
            is Effect.ShowToastMessage -> Toast.makeText(
                context,
                "Toast message #${effect.count}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Pass uiState to composable content for components as source of data
    DashboardScreenContent(
        uiState = uiState,
        onAction = vm::onAction
    )
}

@Composable
private fun DashboardScreenContent(
    uiState: State,
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
                text = uiState.message,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { onAction(Action.OnCountButtonClick) }
            ) {
                Text(
                    text = stringResource(
                        id = R.string.dashboard_screen_toast_message_button_title,
                        uiState.counter.toString()
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { onAction(Action.OnOpenDetailButtonClick) }
            ) {
                Text(
                    text = stringResource(id = R.string.dashboard_screen_back_button_title),
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
            uiState = State(),
            onAction = {},
        )
    }
}
