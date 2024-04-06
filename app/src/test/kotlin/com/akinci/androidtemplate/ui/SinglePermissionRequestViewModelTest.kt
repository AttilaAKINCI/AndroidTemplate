package com.akinci.androidtemplate.ui

import app.cash.turbine.test
import com.akinci.androidtemplate.core.coroutine.MainDispatcherRule
import com.akinci.androidtemplate.core.permission.Permission
import com.akinci.androidtemplate.core.permission.PermissionManager
import com.akinci.androidtemplate.ui.features.permission.single.SinglePermissionRequestViewContract.Action
import com.akinci.androidtemplate.ui.features.permission.single.SinglePermissionRequestViewContract.Effect
import com.akinci.androidtemplate.ui.features.permission.single.SinglePermissionRequestViewContract.State
import com.akinci.androidtemplate.ui.features.permission.single.SinglePermissionRequestViewModel
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherRule::class)
class SinglePermissionRequestViewModelTest {

    private val permissionManagerMock = mockk<PermissionManager>(relaxed = true)

    private lateinit var testedClass: SinglePermissionRequestViewModel

    @BeforeEach
    fun setup() {
        every { permissionManagerMock.isGranted(Permission.Camera) } returns true
        testedClass = SinglePermissionRequestViewModel(
            permissionManager = permissionManagerMock
        )
    }

    @Test
    fun `should send correct initial state when camera permission is provided`() = runTest {
        every { permissionManagerMock.isGranted(Permission.Camera) } returns true

        testedClass = SinglePermissionRequestViewModel(permissionManager = permissionManagerMock)

        testedClass.state.test {
            awaitItem() shouldBe State(
                isPermissionGranted = true,
                isCameraPermissionRationaleDialogVisible = false
            )
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should send correct initial state when camera permission is declined`() = runTest {
        every { permissionManagerMock.isGranted(Permission.Camera) } returns false

        testedClass = SinglePermissionRequestViewModel(permissionManager = permissionManagerMock)

        testedClass.state.test {
            awaitItem() shouldBe State(
                isPermissionGranted = false,
                isCameraPermissionRationaleDialogVisible = false
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
        verify(exactly = 2) { permissionManagerMock.isGranted(Permission.Camera) }
    }

    @Test
    fun `should verify permission when OnCameraPermissionGranted is triggered`() = runTest {
        testedClass.onAction(Action.OnCameraPermissionGranted)
        // initial check + lifecycle change check
        verify(exactly = 2) { permissionManagerMock.isGranted(Permission.Camera) }
    }

    @Test
    fun `should send NavigateToAppSettings when OnOpenAppSettingsButtonClick is triggered`() =
        runTest {
            testedClass.onAction(Action.OnOpenAppSettingsButtonClick)

            testedClass.state.test {
                awaitItem().isCameraPermissionRationaleDialogVisible shouldBe false
                ensureAllEventsConsumed()
            }

            testedClass.effect.test {
                awaitItem() shouldBe Effect.NavigateToAppSettings
                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `should not send RequestCameraPermission effect when OnRequestPermissionButtonClick is triggered and permission is provided`() =
        runTest {
            every { permissionManagerMock.isGranted(Permission.Camera) } returns true

            testedClass.onAction(Action.OnRequestPermissionButtonClick)

            testedClass.effect.test {
                expectNoEvents()
            }
        }

    @Test
    fun `should send RequestCameraPermission effect when OnRequestPermissionButtonClick is triggered and permission is not provided`() =
        runTest {
            every { permissionManagerMock.isGranted(Permission.Camera) } returns false

            testedClass.onAction(Action.OnRequestPermissionButtonClick)

            testedClass.effect.test {
                awaitItem() shouldBe Effect.RequestCameraPermission
                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `should show rationale when OnCameraPermissionRationaleShow is triggered`() = runTest {
        testedClass.onAction(Action.OnCameraPermissionRationaleShow)

        testedClass.state.test {
            awaitItem().isCameraPermissionRationaleDialogVisible shouldBe true
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should hide rationale when OnCameraPermissionRationaleDismiss is triggered`() = runTest {
        testedClass.onAction(Action.OnCameraPermissionRationaleDismiss)

        testedClass.state.test {
            awaitItem().isCameraPermissionRationaleDialogVisible shouldBe false
            ensureAllEventsConsumed()
        }
    }
}
