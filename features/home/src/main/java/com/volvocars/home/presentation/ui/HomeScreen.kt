package com.volvocars.home.presentation.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.volvocars.home.presentation.HomeViewModel
import com.volvocars.home.presentation.component.CityListView
import com.volvocars.home.presentation.view.WeatherItemModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onItemClickListener: (WeatherItemModel) -> Unit = {}
) {
    val cityItems by viewModel.cityItems.collectAsState()

    Scaffold {
        CityListView(cityItems, onItemClickListener)
    }
}