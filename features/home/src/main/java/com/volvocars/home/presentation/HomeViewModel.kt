package com.volvocars.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.volvocars.home.base.BaseViewModel
import com.volvocars.home.domain.usecase.CityModel
import com.volvocars.home.domain.usecase.CityWeatherRequestModel
import com.volvocars.home.domain.usecase.CityWeatherUseCase
import com.volvocars.home.presentation.view.WeatherItemModel
import com.volvocars.home.presentation.view.WeatherItemState
import com.volvocars.navigation.INavigationManager
import com.volvocars.navigation.destinations.DetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val keyAPi = "2119bbe90ee91f6c3171ce4ac9cae10a"

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val navigationManager: INavigationManager,
    private val cityWeatherUseCase: CityWeatherUseCase
) : BaseViewModel() {

    private val _cityItems = mutableStateListOf<WeatherItemModel>()
    private val tempCityItems = mutableListOf<WeatherItemModel>()
    val cityItems: List<WeatherItemModel> = _cityItems

    init {
        initCityList()
    }

    fun initCityList() {
        viewModelScope.launch {
            _cityItems.addAll(
                mutableListOf(
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

    fun getWeathers() {
        var counter = 0
        removeAllJob()

        track {
            val cities: ArrayList<CityModel> = arrayListOf<CityModel>().apply {
                _cityItems.forEachIndexed { index, cityModel ->
                    add(CityModel(index, cityModel.name))
                }
            }
            val cityWeatherRequestModel = CityWeatherRequestModel(cities)

            cityWeatherUseCase.executeAsync(cityWeatherRequestModel)
                .collect { response ->
                    _cityItems.forEach { item ->
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
                            tempCityItems.add(item)
                        }
                    }
                    counter++
                    if (counter == 6) {
                        _cityItems.clear()
                        tempCityItems.forEach {
                            _cityItems.add(it)
                        }
                    }
                }
        }
    }


    fun onItemClickListener(weatherItemModel: WeatherItemModel) {
        viewModelScope.launch {
            navigationManager.navigateTo(
                DetailsDestination.createDetailsRoute(
                    cityName = weatherItemModel.name,
                    weather = weatherItemModel.data?.weather?.get(0)?.main!!,
                    temperature = weatherItemModel.data?.main?.temp?.minus(273.15)?.toInt()
                        .toString(),
                    temperatureMin = weatherItemModel.data?.main?.tempMin?.minus(273.15)?.toInt()
                        .toString(),
                    temperatureMax = weatherItemModel.data?.main?.tempMax?.minus(273.15)?.toInt()
                        .toString(),
                    pressure = weatherItemModel.data?.main?.pressure
                        .toString(),
                    humidity = weatherItemModel.data?.main?.humidity
                        .toString(),
                    windSpeed = weatherItemModel.data?.wind?.speed
                        .toString(),
                )
            )
        }
    }

}