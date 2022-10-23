package com.volvocars.home.di

import com.volvocars.home.data.WeatherRepositoryImpl
import com.volvocars.home.data.api.WeatherApiService
import com.volvocars.home.data.datasource.WeatherRemoteDatasource
import com.volvocars.home.data.datasource.WeatherRemoteDatasourceImpl
import com.volvocars.home.domain.repo.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        remote: WeatherRemoteDatasource
    ): WeatherRepository {
        return WeatherRepositoryImpl(remote = remote)
    }

    @Singleton
    @Provides
    fun providesWeatherRemoteData(@NormalRetrofitClient retrofit: Retrofit): WeatherRemoteDatasource {
        return WeatherRemoteDatasourceImpl(api = retrofit.create(WeatherApiService::class.java))
    }

}
