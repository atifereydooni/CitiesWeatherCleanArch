package com.volvocars.home.presentation.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.volvocars.home.presentation.HomeViewModel
import com.volvocars.home.presentation.component.CityListView

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val cityItems by viewModel.cityItems.collectAsState()

    Scaffold {
        CityListView(cityItems)
    }
}