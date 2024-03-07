package com.akinci.androidtemplate.core.di

import com.akinci.androidtemplate.core.coroutine.ContextProvider
import com.akinci.androidtemplate.core.coroutine.ContextProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  AppModule is the core HILT module for dependency injections
 * **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContextProvider(): ContextProvider = ContextProviderImpl()
}
