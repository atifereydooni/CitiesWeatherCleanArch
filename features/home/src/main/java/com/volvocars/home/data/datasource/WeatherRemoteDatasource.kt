package com.volvocars.home.data.datasource

import com.volvocars.home.domain.entity.WeatherResponseEntity


interface WeatherRemoteDatasource {

    suspend fun getCityWeather(cityName: String, apiKey: String): WeatherResponseEntity

}
