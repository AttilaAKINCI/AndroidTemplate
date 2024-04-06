package com.akinci.androidtemplate.ui

import app.cash.turbine.test
import com.akinci.androidtemplate.core.coroutine.MainDispatcherRule
import com.akinci.androidtemplate.ui.features.splash.SplashViewContract.Effect
import com.akinci.androidtemplate.ui.features.splash.SplashViewModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherRule::class)
class SplashViewModelTest {

    private lateinit var testedClass: SplashViewModel

    @BeforeEach
    fun setup() {
        testedClass = SplashViewModel()
    }

    @Test
    fun `should send complete event when initialization is completed`() = runTest {
        testedClass.effect.test {
            awaitItem() shouldBe Effect.Completed
            ensureAllEventsConsumed()
        }
    }
}
