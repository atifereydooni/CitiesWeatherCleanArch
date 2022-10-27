package com.volvocars.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.volvocars.home.domain.entity.WeatherResponseEntity
import com.volvocars.home.domain.repo.WeatherRepository
import com.volvocars.home.domain.usecase.CityModel
import com.volvocars.home.domain.usecase.CityWeatherResponseModel
import com.volvocars.home.domain.usecase.CityWeatherUseCase
import com.volvocars.home.domain.usecase.WeatherUseCase
import com.volvocars.home.presentation.view.WeatherItemState
import com.volvocars.navigation.INavigationManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: HomeViewModel

    @RelaxedMockK
    private lateinit var navigationManager: INavigationManager

    private lateinit var cityWeatherUseCase: CityWeatherUseCase

    private lateinit var weatherUseCase: WeatherUseCase

    @RelaxedMockK
    private lateinit var repository: WeatherRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        weatherUseCase = spyk(WeatherUseCase(repository))
        cityWeatherUseCase = spyk(CityWeatherUseCase(weatherUseCase))
        viewModel = spyk(HomeViewModel(navigationManager, cityWeatherUseCase))
    }

    @Test
    fun initViewModelTest() {
        assert(viewModel.cityItems.size == 6)
    }

    @Test
    fun getWeathersInvoked() {
        testCoroutineDispatcher.runBlockingTest {
            viewModel.initCityList()
            verify { viewModel.getWeathers() }
        }
    }

    @Test
    fun getWeathersTest() {
        testCoroutineDispatcher.runBlockingTest {
            coEvery {
                cityWeatherUseCase.executeAsync(any())
            } returns flow {
                emit(
                    CityWeatherResponseModel(
                        CityModel(0, "Gothenburg"),
                        WeatherResponseEntity()
                    )
                )
            }

            viewModel.getWeathers()

            assert(viewModel.cityItems.first().state == WeatherItemState.Success)

        }
    }
}