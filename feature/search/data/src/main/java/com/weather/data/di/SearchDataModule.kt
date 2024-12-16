package com.weather.data.di

import com.weather.data.remote.SearchServiceApi
import com.weather.data.repository.SearchRepositoryImp
import com.weather.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = ""

@InstallIn(SingletonComponent::class)
@Module
object SearchDataModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesSearchAPI(retrofit: Retrofit) : SearchServiceApi {
        return retrofit.create(SearchServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun providesSearchRepoImp(searchServiceApi: SearchServiceApi) : SearchRepository{
        return SearchRepositoryImp(searchServiceApi)
    }

}