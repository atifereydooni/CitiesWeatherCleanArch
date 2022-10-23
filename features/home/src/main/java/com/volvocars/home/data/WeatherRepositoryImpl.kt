package com.volvocars.home.data

import com.volvocars.home.data.datasource.WeatherRemoteDatasource
import com.volvocars.home.domain.entity.WeatherResponseEntity
import com.volvocars.home.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val remote: WeatherRemoteDatasource) :
    WeatherRepository {

    override suspend fun getCityWeather(
        cityName: String,
        apiKey: String
    ): Flow<WeatherResponseEntity> {
        return flowOf(remote.getCityWeather(cityName, apiKey))
    }

}
