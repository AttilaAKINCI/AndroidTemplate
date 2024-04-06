package com.akinci.androidtemplate.ui

import app.cash.turbine.test
import com.akinci.androidtemplate.core.coroutine.MainDispatcherRule
import com.akinci.androidtemplate.core.coroutine.TestContextProvider
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.Action
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.Effect
import com.akinci.androidtemplate.ui.features.detail.DetailViewContract.State
import com.akinci.androidtemplate.ui.features.detail.DetailViewModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherRule::class)
class DetailViewModelTest {

    private val coroutineContextProvider = TestContextProvider()

    private lateinit var testedClass: DetailViewModel

    @BeforeEach
    fun setup() {
        testedClass = DetailViewModel(
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
    fun `should send navigate back effect when OnBackButtonClick triggered`() = runTest {
        testedClass.onAction(Action.OnBackButtonClick)

        testedClass.effect.test {
            awaitItem() shouldBe Effect.NavigateBack
            ensureAllEventsConsumed()
        }
    }

    private fun getExpectedState() = State(
        message = "Welcome to Detail Screen",
    )
}
