package com.volvocars.assignment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.volvocars.details.presentation.DetailsScreen
import com.volvocars.details.presentation.navigation.DetailsNavigationModel
import com.volvocars.home.presentation.HomeViewModel
import com.volvocars.home.presentation.ui.HomeScreen
import com.volvocars.navigation.destinations.DetailsDestination
import com.volvocars.navigation.destinations.HomeDestination
import com.volvocars.navigation.destinations.NavigationDestination

private fun composableDestinations(): Map<NavigationDestination, @Composable () -> Unit> =
    mapOf(
        HomeDestination to {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel, viewModel::onItemClickListener)
        },
        DetailsDestination to {
            DetailsScreen()
        }
    )

fun NavGraphBuilder.addComposableDestinations() {
    composableDestinations().forEach { entry ->
        val destination = entry.key
        composable(
            destination.route, destination.arguments, destination.deepLinks
        ) {
            if (destination.route.startsWith(DetailsDestination.DETAILS_ROUTE)) {
                DetailsScreen(
                    DetailsNavigationModel(
                        cityName = it.arguments?.getString(DetailsDestination.Params.CITY_NAME),
                        weather = it.arguments?.getString(DetailsDestination.Params.WEATHER),
                        temperature = it.arguments?.getString(DetailsDestination.Params.TEMPERATURE),
                        temperatureMin = it.arguments?.getString(DetailsDestination.Params.TEMPERATURE_MIN),
                        temperatureMax = it.arguments?.getString(DetailsDestination.Params.TEMPERATURE_MAX),
                        pressure = it.arguments?.getString(DetailsDestination.Params.PRESSURE),
                        humidity = it.arguments?.getString(DetailsDestination.Params.HUMIDITY),
                        windSpeed = it.arguments?.getString(DetailsDestination.Params.WIND_SPEED)
                    )
                )
            } else {
                entry.value()
            }
        }
    }
}

