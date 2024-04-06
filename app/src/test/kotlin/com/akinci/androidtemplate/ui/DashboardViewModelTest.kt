package com.akinci.androidtemplate.ui

import app.cash.turbine.test
import com.akinci.androidtemplate.core.coroutine.MainDispatcherRule
import com.akinci.androidtemplate.core.coroutine.TestContextProvider
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewModel
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.State
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Action
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Effect
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherRule::class)
class DashboardViewModelTest {

    private val coroutineContextProvider = TestContextProvider()

    private lateinit var testedClass: DashboardViewModel

    @BeforeEach
    fun setup() {
        testedClass = DashboardViewModel(
            contextProvider = coroutineContextProvider,
        )
    }

    @Test
    fun `should provide initial state when vm is initialised`() = runTest {
        testedClass.state.test {
            awaitItem() shouldBe getExpectedState()
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should update state when OnCountButtonClick triggered`() = runTest {
        testedClass.onAction(Action.OnCountButtonClick)

        testedClass.state.test {
            awaitItem() shouldBe State(
                message = "Welcome to Dashboard Screen",
                counter = 2,
            )
            ensureAllEventsConsumed()
        }

        testedClass.effect.test {
            awaitItem() shouldBe Effect.ShowToastMessage(count = 2)
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should send NavigateDetailScreen effect when OnOpenDetailButtonClick triggered`() = runTest {
        testedClass.onAction(Action.OnOpenDetailButtonClick)

        testedClass.effect.test {
            awaitItem() shouldBe Effect.NavigateDetailScreen
            ensureAllEventsConsumed()
        }
    }

    private fun getExpectedState() = State(
        message = "Welcome to Dashboard Screen",
        counter = 1,
    )
}