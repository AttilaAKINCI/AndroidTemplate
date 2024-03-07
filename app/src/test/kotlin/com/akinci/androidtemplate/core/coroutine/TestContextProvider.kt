package com.akinci.androidtemplate.core.coroutine

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
class TestContextProvider : ContextProvider {
    override val main: CoroutineContext = UnconfinedTestDispatcher()
    override val io: CoroutineContext = UnconfinedTestDispatcher()
}
