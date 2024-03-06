package com.akinci.androidtemplate.core.coroutine

import com.akinci.androidtemplate.core.di.AppModule
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * ContextProvider is an interface for coroutine context provider.
 * provider will be served via HILT injection, implementations can be seen at [AppModule]
 *
 * This class provides:
 * - Using inheritance feature we can separate app coroutine context from test coroutine context
 * **/

interface ContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
}

class ContextProviderImpl : ContextProvider {
    override val main: CoroutineContext by lazy { Dispatchers.Main }
    override val io: CoroutineContext by lazy { Dispatchers.IO }
}
