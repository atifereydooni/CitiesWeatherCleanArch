package com.volvocars.assignment

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.volvocars.navigation.destinations.NavigationDestination

private fun composableDestinations(): Map<NavigationDestination, @Composable () -> Unit> =
    mapOf(

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

