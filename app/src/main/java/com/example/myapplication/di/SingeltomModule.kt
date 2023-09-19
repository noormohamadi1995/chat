package com.example.myapplication.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideJson() = Json {
        encodeDefaults = false
        explicitNulls = true
        ignoreUnknownKeys = true
        isLenient = true
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractSingletonModule {

}