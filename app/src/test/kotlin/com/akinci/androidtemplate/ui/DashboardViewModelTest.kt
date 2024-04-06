package com.akinci.androidtemplate.ui

import app.cash.turbine.test
import com.akinci.androidtemplate.core.coroutine.MainDispatcherRule
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Action
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewContract.Effect
import com.akinci.androidtemplate.ui.features.dashboard.DashboardViewModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherRule::class)
class DashboardViewModelTest {

    private lateinit var testedClass: DashboardViewModel

    @BeforeEach
    fun setup() {
        testedClass = DashboardViewModel()
    }

    @Test
    fun `should send NavigateSinglePermissionScreen event when OnOpenSinglePermissionScreenButtonClick triggered`() =
        runTest {
            testedClass.onAction(Action.OnOpenSinglePermissionScreenButtonClick)

            testedClass.effect.test {
                awaitItem() shouldBe Effect.NavigateSinglePermissionScreen
                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `should send NavigateMultiplePermissionScreen event when OnOpenMultiplePermissionScreenButtonClick triggered`() =
        runTest {
            testedClass.onAction(Action.OnOpenMultiplePermissionScreenButtonClick)

            testedClass.effect.test {
                awaitItem() shouldBe Effect.NavigateMultiplePermissionScreen
                ensureAllEventsConsumed()
            }
        }
}
