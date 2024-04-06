package com.akinci.androidtemplate.core.permission

import android.content.Context
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PermissionManagerTest {

    private val context = mockk<Context>(relaxed = true)

    private lateinit var spyTestedClass: PermissionManager

    @BeforeEach
    fun setup() {
        spyTestedClass = spyk(
            PermissionManager(context = context)
        )
    }

    @Test
    fun `should return true when calendar permissions are provided`() = runTest {
        every { spyTestedClass.isGranted(Permission.CalendarRead) } returns true
        every { spyTestedClass.isGranted(Permission.CalenderWrite) } returns true

        val result = spyTestedClass.isCalendarPermissionGranted()
        result shouldBe true
    }

    @Test
    fun `should return false when only calendar write permission is provided`() = runTest {
        every { spyTestedClass.isGranted(Permission.CalendarRead) } returns false
        every { spyTestedClass.isGranted(Permission.CalenderWrite) } returns true

        val result = spyTestedClass.isCalendarPermissionGranted()
        result shouldBe false
    }

    @Test
    fun `should return false when only calendar read permission is provided`() = runTest {
        every { spyTestedClass.isGranted(Permission.CalendarRead) } returns true
        every { spyTestedClass.isGranted(Permission.CalenderWrite) } returns false

        val result = spyTestedClass.isCalendarPermissionGranted()
        result shouldBe false
    }
}
