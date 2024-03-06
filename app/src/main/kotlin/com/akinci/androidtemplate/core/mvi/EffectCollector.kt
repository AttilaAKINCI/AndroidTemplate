package com.akinci.androidtemplate.core.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * EffectCollector provides safe collection of side effects on composable side
 * which are send from viewModels
 * **/
@Composable
fun <Effect> EffectCollector(
    effect: Flow<Effect>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = Dispatchers.Main.immediate,
    onEffect: suspend CoroutineScope.(effect: Effect) -> Unit,
) {
    LaunchedEffect(effect, lifecycleOwner) {
        effect
            .flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
            .flowOn(context)
            .collect {
                onEffect(it)
            }
    }
}
