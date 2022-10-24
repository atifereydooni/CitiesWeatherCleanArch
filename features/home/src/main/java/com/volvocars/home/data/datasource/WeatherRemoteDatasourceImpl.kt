package com.volvocars.home.data.datasource

import com.volvocars.home.data.api.WeatherApiService
import com.volvocars.home.domain.entity.WeatherResponseEntity
import retrofit2.HttpException

class WeatherRemoteDatasourceImpl(private val api: WeatherApiService) :
    WeatherRemoteDatasource {

    override suspend fun getCityWeather(
        cityName: String,
        apiKey: String
    ): Result<WeatherResponseEntity> {
        return handleRequest {
            api.getCityWeather(cityName, apiKey)
        }
    }

}

suspend fun <T : Any> handleRequest(requestFunc: suspend () -> T): Result<T> {
    return try {
        Result.success(requestFunc.invoke())
    } catch (e: HttpException) {
        when {
            e.code() == 400 -> Result.failure(Throwable(e.message()))
            else -> Result.failure(e)
        }
    }
}
