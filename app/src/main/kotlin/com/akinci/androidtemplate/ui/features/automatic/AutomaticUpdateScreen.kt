package com.akinci.androidtemplate.ui.features.automatic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akinci.androidtemplate.R
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.core.mvi.EffectCollector
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.akinci.androidtemplate.ui.features.automatic.AutomaticUpdateViewContract.Action
import com.akinci.androidtemplate.ui.features.automatic.AutomaticUpdateViewContract.Effect
import com.akinci.androidtemplate.ui.features.automatic.AutomaticUpdateViewContract.State
import com.akinci.androidtemplate.ui.features.destinations.ManualUpdateScreenDestination
import com.akinci.androidtemplate.ui.navigation.animations.FadeInOutAnimation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = FadeInOutAnimation::class)
@Composable
fun AutomaticUpdateScreen(
    navigator: DestinationsNavigator,
    vm: AutomaticUpdateViewModel = hiltViewModel(),
) {
    // lifecycle collection for ui state
    val uiState: State by vm.state.collectAsStateWithLifecycle()

    // side effects will be handled in EffectCollector block
    EffectCollector(effect = vm.effect) { effect ->
        when (effect) {
            Effect.NavigateManualUpdateScreen -> navigator.navigate(ManualUpdateScreenDestination)
        }
    }

    // Pass uiState to composable content for components as source of data
    AutomaticUpdateScreenContent(
        uiState = uiState,
        onAction = vm::onAction
    )
}

@Composable
private fun AutomaticUpdateScreenContent(
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
                text = stringResource(id = R.string.automatic_update_screen_screen_title),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .border(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        shape = MaterialTheme.shapes.large,
                    ),
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.automatic_update_screen_screen_description),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier.size(64.dp),
                    imageVector = Icons.Default.Brightness1,
                    contentDescription = null,
                    tint = uiState.connectionStatus.color,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = uiState.connectionStatus.messageId),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { onAction(Action.OnGoToManualUpdateButtonClick) }
            ) {
                Text(
                    text = stringResource(id = R.string.automatic_update_screen_back_button_title),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@UIModePreviews
@Composable
private fun AutomaticUpdateScreenPreview() {
    AppTheme {
        AutomaticUpdateScreenContent(
            uiState = State(),
            onAction = {},
        )
    }
}
