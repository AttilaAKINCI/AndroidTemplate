package com.akinci.androidtemplate.ui.navigation

import com.akinci.androidtemplate.ui.features.main.MainScreen

/**
 * DestinationExt should be used to define logics which effect base layout in [MainScreen] composable
 **/

/**
 * Usage examples of DestinationExt:
 *
 * - MainScreen holds main NavHost and it can be in a Scaffold composable. We can provide data / parameters
 *   to organise scaffold (topBar, bottomBar, window inset settings or etc.) differently
 * - System bar content color and background color requirements can be different for each destination.
 *   Rather than implementing it in all screens we can adjust in on [MainScreen] composable checking
 *   current navigation destination
 * **/