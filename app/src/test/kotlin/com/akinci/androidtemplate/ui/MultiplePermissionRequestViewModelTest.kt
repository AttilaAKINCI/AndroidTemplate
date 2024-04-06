package com.akinci.androidtemplate.ui

import app.cash.turbine.test
import com.akinci.androidtemplate.core.coroutine.MainDispatcherRule
import com.akinci.androidtemplate.core.permission.PermissionManager
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Action
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Effect
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.State
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewModel
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherRule::class)
class MultiplePermissionRequestViewModelTest {

    private val permissionManagerMock = mockk<PermissionManager>(relaxed = true)

    private lateinit var testedClass: MultiplePermissionRequestViewModel

    @BeforeEach
    fun setup() {
        every { permissionManagerMock.isCalendarPermissionGranted() } returns true
        testedClass = MultiplePermissionRequestViewModel(
            permissionManager = permissionManagerMock
        )
    }

    @Test
    fun `should send correct initial state when calendar permission is provided`() = runTest {
        every { permissionManagerMock.isCalendarPermissionGranted() } returns true

        testedClass = MultiplePermissionRequestViewModel(permissionManager = permissionManagerMock)

        testedClass.state.test {
            awaitItem() shouldBe State(
                isPermissionGranted = true,
                isCalendarPermissionRationaleDialogVisible = false
            )
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should send correct initial state when calendar permission is declined`() = runTest {
        every { permissionManagerMock.isCalendarPermissionGranted() } returns false

        testedClass = MultiplePermissionRequestViewModel(permissionManager = permissionManagerMock)

        testedClass.state.test {
            awaitItem() shouldBe State(
                isPermissionGranted = false,
                isCalendarPermissionRationaleDialogVisible = false
            )
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should send NavigateBack when OnNavigateBackButtonClick is triggered`() = runTest {
        testedClass.onAction(Action.OnNavigateBackButtonClick)

        testedClass.effect.test {
            awaitItem() shouldBe Effect.NavigateBack
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should verify permission when OnLifeCycleStart is triggered`() = runTest {
        testedClass.onAction(Action.OnLifeCycleStart)
        // initial check + lifecycle change check
        verify(exactly = 2) { permissionManagerMock.isCalendarPermissionGranted() }
    }

    @Test
    fun `should verify permission when OnCalendarPermissionGranted is triggered`() = runTest {
        testedClass.onAction(Action.OnCalendarPermissionGranted)
        // initial check + lifecycle change check
        verify(exactly = 2) { permissionManagerMock.isCalendarPermissionGranted() }
    }

    @Test
    fun `should send NavigateToAppSettings when OnOpenAppSettingsButtonClick is triggered`() =
        runTest {
            testedClass.onAction(Action.OnOpenAppSettingsButtonClick)

            testedClass.state.test {
                awaitItem().isCalendarPermissionRationaleDialogVisible shouldBe false
                ensureAllEventsConsumed()
            }

            testedClass.effect.test {
                awaitItem() shouldBe Effect.NavigateToAppSettings
                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `should not send RequestCalendarPermission effect when OnRequestPermissionButtonClick is triggered and permission is provided`() =
        runTest {
            every { permissionManagerMock.isCalendarPermissionGranted() } returns true

            testedClass.onAction(Action.OnRequestPermissionButtonClick)

            testedClass.effect.test {
                expectNoEvents()
            }
        }

    @Test
    fun `should send RequestCalendarPermission effect when OnRequestPermissionButtonClick is triggered and permission is not provided`() =
        runTest {
            every { permissionManagerMock.isCalendarPermissionGranted() } returns false

            testedClass.onAction(Action.OnRequestPermissionButtonClick)

            testedClass.effect.test {
                awaitItem() shouldBe Effect.RequestCalendarPermission
                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `should show rationale when OnCalendarPermissionRationaleShow is triggered`() = runTest {
        testedClass.onAction(Action.OnCalendarPermissionRationaleShow)

        testedClass.state.test {
            awaitItem().isCalendarPermissionRationaleDialogVisible shouldBe true
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should hide rationale when OnCalendarPermissionRationaleDismiss is triggered`() = runTest {
        testedClass.onAction(Action.OnCalendarPermissionRationaleDismiss)

        testedClass.state.test {
            awaitItem().isCalendarPermissionRationaleDialogVisible shouldBe false
            ensureAllEventsConsumed()
        }
    }
}
