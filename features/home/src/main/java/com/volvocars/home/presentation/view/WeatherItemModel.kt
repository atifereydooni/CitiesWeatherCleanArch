package com.volvocars.home.presentation.view

import com.volvocars.home.domain.entity.WeatherResponseEntity


data class WeatherItemModel(
    val name: String,
    var state: WeatherItemState,
    var icon: String? = null,
    var data: WeatherResponseEntity? = null,
)

enum class WeatherItemState {
    Loading, Success, Failed
}