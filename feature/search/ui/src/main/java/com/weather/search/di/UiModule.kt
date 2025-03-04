package com.weather.search.di

import com.weather.search.navigation.SearchFeatureAPI
import com.weather.search.navigation.SearchFeatureAPIImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    @Singleton
    fun provideSearchFeatureAPI() : SearchFeatureAPI {
        return SearchFeatureAPIImp()
    }
}