package com.volvocars.home.presentation

import androidx.lifecycle.viewModelScope
import com.volvocars.home.base.BaseViewModel
import com.volvocars.home.domain.usecase.CityModel
import com.volvocars.home.domain.usecase.CityWeatherRequestModel
import com.volvocars.home.domain.usecase.CityWeatherUseCase
import com.volvocars.home.presentation.view.WeatherItemModel
import com.volvocars.home.presentation.view.WeatherItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val keyAPi = "2119bbe90ee91f6c3171ce4ac9cae10a"

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val cityWeatherUseCase: CityWeatherUseCase
) : BaseViewModel() {

    private val _cityItems = MutableStateFlow<List<WeatherItemModel>>(arrayListOf())
    val cityItems: StateFlow<List<WeatherItemModel>> = _cityItems.asStateFlow()

    init {
        initCityList()
    }

    private fun initCityList() {
        viewModelScope.launch {
            _cityItems.emit(
                arrayListOf(
                    WeatherItemModel("Gothenburg", WeatherItemState.Loading),
                    WeatherItemModel("Stockholm", WeatherItemState.Loading),
                    WeatherItemModel("Mountain View", WeatherItemState.Loading),
                    WeatherItemModel("London", WeatherItemState.Loading),
                    WeatherItemModel("New York", WeatherItemState.Loading),
                    WeatherItemModel("Berlin", WeatherItemState.Loading)
                )
            )
            getWeathers()
        }
    }

    private fun getWeathers() {

        removeAllJob()

        track {
            val cities: ArrayList<CityModel> = arrayListOf<CityModel>().apply {
                _cityItems.value.forEachIndexed { index, cityModel ->
                    add(CityModel(index, cityModel.name))
                }
            }
            val cityWeatherRequestModel = CityWeatherRequestModel(cities)

            cityWeatherUseCase.executeAsync(cityWeatherRequestModel)
                .collect { response ->
                    _cityItems.getAndUpdate { weatherItemModel ->
                        weatherItemModel.forEach { item ->
                            if (item.name == response?.cityModel?.name) {
                                item.data = response.weather
                                item.state = WeatherItemState.Success
                                item.icon =
                                    if (item.data?.weather?.get(0)?.icon?.isNotEmpty() == true) {
                                        "https://openweathermap.org/img/wn/${
                                            item.data?.weather?.get(
                                                0
                                            )?.icon
                                        }@2x.png"
                                    } else {
                                        null
                                    }
                            }
                        }
                        weatherItemModel
                    }
                }
        }
    }

}