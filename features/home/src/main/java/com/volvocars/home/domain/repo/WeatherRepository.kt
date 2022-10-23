package com.volvocars.home.domain.repo

import com.volvocars.home.domain.entity.WeatherResponseEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCityWeather(cityName: String, apiKey: String): Flow<WeatherResponseEntity>

}
