package com.volvocars.home.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.volvocars.home.data.WeatherRepositoryImpl
import com.volvocars.home.data.datasource.WeatherRemoteDatasource
import com.volvocars.home.domain.entity.WeatherResponseEntity
import com.volvocars.home.domain.repo.WeatherRepository
import com.volvocars.home.presentation.keyAPi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class WeatherUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var repository: WeatherRepository

    private lateinit var weatherUseCase: WeatherUseCase

    @RelaxedMockK
    private lateinit var weatherRemoteDatasource: WeatherRemoteDatasource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        repository = spyk(WeatherRepositoryImpl(weatherRemoteDatasource))
        weatherUseCase = spyk(WeatherUseCase(repository))
    }

    @Test
    fun getCityWeatherTest() {
        val cityName = "London"
        testCoroutineDispatcher.runBlockingTest {
            coEvery {
                repository.getCityWeather(cityName, keyAPi)
            } returns Result.success(WeatherResponseEntity())

            val result = weatherUseCase.getCityWeather(cityName, keyAPi)
            assert(result.isSuccess)
        }
    }

    @Test
    fun getCityWeatherTest_ResponseIsNotNull() {
        val cityName = "London"
        testCoroutineDispatcher.runBlockingTest {
            coEvery {
                repository.getCityWeather(cityName, keyAPi)
            } returns Result.success(WeatherResponseEntity(name = cityName))

            val result = weatherUseCase.getCityWeather(cityName, keyAPi)
            assert(result.getOrNull() != null)
        }
    }
}