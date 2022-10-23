package com.volvocars.home.data.datasource

import com.volvocars.home.data.api.WeatherApiService
import com.volvocars.home.domain.entity.WeatherResponseEntity

class WeatherRemoteDatasourceImpl(private val api: WeatherApiService) :
    WeatherRemoteDatasource {

    override suspend fun getCityWeather(cityName: String, apiKey: String): WeatherResponseEntity {
        return api.getCityWeather(cityName, apiKey)
    }

}
