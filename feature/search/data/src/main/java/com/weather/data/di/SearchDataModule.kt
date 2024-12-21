package com.weather.data.di

import android.util.Log
import com.weather.data.remote.SearchServiceApi
import com.weather.data.repository.SearchRepositoryImp
import com.weather.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://www.themealdb.com/"

@InstallIn(SingletonComponent::class)
@Module
object SearchDataModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesSearchAPI(retrofit: Retrofit) : SearchServiceApi {
        Log.v("API TAG","SearchAPI interface created")
        return retrofit.create(SearchServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun providesSearchRepoImp(searchServiceApi: SearchServiceApi) : SearchRepository{
        Log.v("LOGGED","SearchRepoImpl")
        return SearchRepositoryImp(searchServiceApi)
    }

}