package com.akinci.androidtemplate.ui.features.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinci.androidtemplate.R
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.core.mvi.EffectCollector
import com.akinci.androidtemplate.ui.ds.components.InfiniteLottieAnimation
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.akinci.androidtemplate.ui.features.NavGraphs
import com.akinci.androidtemplate.ui.features.destinations.AutomaticUpdateScreenDestination
import com.akinci.androidtemplate.ui.features.splash.SplashViewContract.Effect
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    vm: SplashViewModel = hiltViewModel(),
) {
    EffectCollector(effect = vm.effect) { effect ->
        when (effect) {
            Effect.Completed -> navigator.navigate(AutomaticUpdateScreenDestination) {
                popUpTo(NavGraphs.root.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    SplashScreenContent()
}

@Composable
private fun SplashScreenContent() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InfiniteLottieAnimation(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .aspectRatio(1f),
                    animationId = R.raw.blocks
                )
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displaySmall,
                )
            }
        }
    }
}

@UIModePreviews
@Composable
private fun SplashScreenPreview() {
    AppTheme {
        SplashScreenContent()
    }
}
