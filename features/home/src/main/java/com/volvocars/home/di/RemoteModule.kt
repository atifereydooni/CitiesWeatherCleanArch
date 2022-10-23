package com.volvocars.home.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class BaseUrl

@Qualifier
annotation class LoggerInterceptorProvider

@Qualifier
annotation class NormalOkHttpClient

@Qualifier
annotation class NormalRetrofitClient

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @BaseUrl
    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://api.openweathermap.org"

    @LoggerInterceptorProvider
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @NormalOkHttpClient
    @Singleton
    @Provides
    fun provideNormalOkHttpClient(
        @LoggerInterceptorProvider loggerInterceptor: Interceptor
    ): OkHttpClient = clientBuilder(loggerInterceptor = loggerInterceptor)

    @NormalRetrofitClient
    @Singleton
    @Provides
    fun provideNormalRetrofitClient(
        @NormalOkHttpClient okHttpClient: OkHttpClient, @BaseUrl baseUrl: String
    ): Retrofit = retrofitBuilder(okHttpClient, baseUrl)

    private fun retrofitBuilder(
        okHttpClient: OkHttpClient, @BaseUrl baseUrl: String
    ): Retrofit = Retrofit.Builder().client(okHttpClient).addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        ).asLenient()
    ).baseUrl(baseUrl).build()

    private fun clientBuilder(
        loggerInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggerInterceptor)

            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
        }
        return client.build()
    }
}
