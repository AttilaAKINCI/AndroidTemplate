package com.akinci.androidtemplate.core.logger

import com.akinci.androidtemplate.core.application.AppConfig
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import timber.log.Timber

class LoggerInitializerTest {

    private val appConfigMock: AppConfig = mockk()

    private lateinit var testedClass: LoggerInitializer

    @BeforeEach
    fun setup() {
        mockkObject(Timber)
        testedClass = LoggerInitializer(appConfigMock)
    }

    @AfterEach
    fun release() {
        unmockkObject(Timber)
    }

    @Test
    fun `should not plant debug tree in release`() {
        val trees = mutableListOf<Timber.Tree>()
        every { Timber.plant(capture(trees)) } returns Unit
        every { appConfigMock.isDebugMode() } returns false

        testedClass.initialize()

        verify(exactly = 0) { Timber.plant(any()) }
        trees.any { it is Timber.DebugTree } shouldBe false
    }

    @Test
    fun `should plant debug tree in debug`() {
        val trees = mutableListOf<Timber.Tree>()
        every { Timber.plant(capture(trees)) } returns Unit
        every { appConfigMock.isDebugMode() } returns true

        testedClass.initialize()

        verify(exactly = 1) { Timber.plant(any()) }
        trees.any { it is Timber.DebugTree } shouldBe true
    }
}
