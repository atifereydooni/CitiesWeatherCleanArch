package com.volvocars.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volvocars.home.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val keyAPi = "2119bbe90ee91f6c3171ce4ac9cae10a"

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    init {
        getWeathers()
    }

    private fun getWeathers() {

        viewModelScope.launch {
            weatherUseCase.getCityWeather(
                "London", keyAPi
            ).collect {
                Log.d("HomeViewModel", it.toString())
            }
        }

    }

}