package com.akinci.androidtemplate.ui.features.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navHostEngine = rememberAnimatedNavHostEngine()

    /* val destination = navController.appCurrentDestinationAsState().value
         ?: NavGraphs.root.startAppDestination

     DestinationsNavHost(
         navController = navController,
         engine = navHostEngine,
         navGraph = NavGraphs.root,
         startRoute = NavGraphs.root.startRoute,
     )*/
}
