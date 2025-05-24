package com.example.weatherforecastapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {

    @Provides
    @Named("API_KEY")
    fun provideApiKey(): String = "59adfad005b1be9e11bd8e2693001219"
}