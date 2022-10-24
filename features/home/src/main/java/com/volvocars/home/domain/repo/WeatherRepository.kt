package com.volvocars.home.domain.repo

import com.volvocars.home.domain.entity.WeatherResponseEntity

interface WeatherRepository {

    suspend fun getCityWeather(cityName: String, apiKey: String): Result<WeatherResponseEntity>

}
