package com.devjethava.composeboilerplate.di

import com.devjethava.composeboilerplate.network.ApiService
import com.devjethava.composeboilerplate.network.ResponseInterceptor
import com.devjethava.composeboilerplate.network.repository.RestApiRepository
import com.devjethava.composeboilerplate.network.repository.RestApiRepositoryImpl
import com.devjethava.composeboilerplate.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val connectTimeout: Long = 180// 20s
        val readTimeout: Long = 180 // 20s

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(logging)
        okHttpClientBuilder.addInterceptor(ResponseInterceptor())
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRestApi(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun getRestApiRepository(
        apiService: ApiService
    ): RestApiRepository = RestApiRepositoryImpl(apiService)
}