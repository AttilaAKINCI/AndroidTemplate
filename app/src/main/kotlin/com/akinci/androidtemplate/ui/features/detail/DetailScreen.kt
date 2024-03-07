package com.akinci.androidtemplate.ui.features.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akinci.androidtemplate.R
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.core.mvi.EffectCollector
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.Action
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.Effect
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.State
import com.akinci.androidtemplate.ui.navigation.animations.SlideHorizontallyAnimation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = SlideHorizontallyAnimation::class)
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    vm: DetailViewModel = hiltViewModel(),
) {
    // lifecycle collection for ui state
    val uiState: State by vm.state.collectAsStateWithLifecycle()

    // side effects will be handled in EffectCollector block
    EffectCollector(effect = vm.effect) { effect ->
        when (effect) {
            Effect.NavigateBack -> navigator.navigateUp()
        }
    }

    DetailScreenContent(
        uiState = uiState,
        onAction = vm::onAction,
    )
}

@Composable
private fun DetailScreenContent(
    uiState: State,
    onAction: (Action) -> Unit
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
            OutlinedButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { onAction(Action.OnBackButtonClick) }
            ) {
                Text(
                    text = stringResource(id = R.string.detail_screen_back_button_title),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@UIModePreviews
@Composable
private fun DetailScreenPreview() {
    AppTheme {
        DetailScreenContent(
            uiState = State(),
            onAction = {},
        )
    }
}
