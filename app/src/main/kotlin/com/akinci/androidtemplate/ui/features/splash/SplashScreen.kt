package com.akinci.androidtemplate.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinci.androidtemplate.core.compose.UIModePreviews
import com.akinci.androidtemplate.core.mvi.EffectCollector
import com.akinci.androidtemplate.ui.ds.theme.AppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.akinci.androidtemplate.ui.features.splash.SplashViewContract.Effect

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    vm: SplashViewModel = hiltViewModel(),
) {
    EffectCollector(effect = vm.effect) { effect ->
        when (effect) {
            Effect.Completed -> navigator.navigate(DashboardScreenDestination) {
                popUpTo(NavGraphs.root.route) {
                    inclusive = true
                }
            }
        }
    }

    SplashScreenContent()
}

@Composable
private fun SplashScreenContent(

) {

}

@UIModePreviews
@Composable
private fun SplashScreenPreview() {
    AppTheme {
        SplashScreenContent()
    }
}
