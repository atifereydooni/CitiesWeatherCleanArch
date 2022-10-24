package com.volvocars.home.domain.usecase

import com.volvocars.home.domain.entity.WeatherResponseEntity
import com.volvocars.home.presentation.keyAPi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class CityWeatherUseCase(
    private val weatherUseCase: WeatherUseCase
) {

    suspend fun executeAsync(rq: CityWeatherRequestModel): Flow<CityWeatherResponseModel?> {
        return flow {
            rq.cities.forEach {
                emit(getWeatherOfWeatherUseCase(it))
            }
        }
    }

    private suspend fun getWeatherOfWeatherUseCase(
        cityModel: CityModel
    ): CityWeatherResponseModel {
        val result = weatherUseCase.getCityWeather(cityModel.name, keyAPi)
        return CityWeatherResponseModel(cityModel, result.first())
    }
}

data class CityWeatherRequestModel(
    val cities: ArrayList<CityModel>,
)

data class CityWeatherResponseModel(
    val cityModel: CityModel,
    val weather: WeatherResponseEntity?
)

data class CityModel(
    val index: Int,
    val name: String
)
