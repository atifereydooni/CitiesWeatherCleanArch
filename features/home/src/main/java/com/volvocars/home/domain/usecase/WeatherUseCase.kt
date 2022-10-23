package com.volvocars.home.domain.usecase

import com.volvocars.home.domain.entity.WeatherResponseEntity
import com.volvocars.home.domain.repo.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend fun getCityWeather(
        cityName: String,
        apiKey: String
    ): Flow<WeatherResponseEntity> {
        return repository.getCityWeather(cityName, apiKey)
    }
}
