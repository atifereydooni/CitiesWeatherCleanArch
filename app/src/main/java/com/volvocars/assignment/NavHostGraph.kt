package com.volvocars.assignment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.volvocars.home.presentation.HomeViewModel
import com.volvocars.home.presentation.ui.HomeScreen
import com.volvocars.navigation.destinations.HomeDestination
import com.volvocars.navigation.destinations.NavigationDestination

private fun composableDestinations(): Map<NavigationDestination, @Composable () -> Unit> =
    mapOf(
        HomeDestination to {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen()
        }
    )

fun NavGraphBuilder.addComposableDestinations() {
    composableDestinations().forEach { entry ->
        val destination = entry.key
        composable(
            destination.route, destination.arguments, destination.deepLinks
        ) {
            entry.value()
        }
    }
}

