package com.akinci.networkconnectivity.core.di

import com.akinci.networkconnectivity.core.coroutine.ContextProvider
import com.akinci.networkconnectivity.core.coroutine.ContextProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContextProvider(): ContextProvider = ContextProviderImpl()
}
