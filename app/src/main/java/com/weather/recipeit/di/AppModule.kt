package com.weather.recipeit.di


import com.weather.recipeit.Nav.NavSubGraphs
import com.weather.search.navigation.SearchFeatureAPI
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
    fun provideNavSubGraph(
        searchFeatureAPI: SearchFeatureAPI
    ) : NavSubGraphs = NavSubGraphs(searchFeatureAPI)


}