package com.volvocars.home.data.api

import com.volvocars.home.domain.entity.WeatherResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("/data/2.5/weather")
    suspend fun getCityWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): WeatherResponseEntity

}
