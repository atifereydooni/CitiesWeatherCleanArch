package com.volvocars.assignment

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.volvocars.navigation.INavigationManager
import com.volvocars.navigation.NavigationEvent
import com.volvocars.navigation.destinations.HomeDestination
import kotlinx.coroutines.flow.collect

@Composable
fun MainScaffold(
    navigationManager: INavigationManager
) {
    val navController = rememberAnimatedNavController()
    val scaffoldState = rememberScaffoldState()
    NavigationEvent(
        navController = navController,
        navigationManager = navigationManager
    )
    Scaffold(scaffoldState = scaffoldState) {
        AnimatedNavHost(
            navController = navController,
            startDestination = HomeDestination.route,
            builder = {
                addComposableDestinations()
            }
        )
    }
}

@Composable
private fun NavigationEvent(
    navController: NavHostController,
    navigationManager: INavigationManager
) {
    LaunchedEffect(navController) {
        navigationManager.navigationEvents.collect {
            when (val event = it) {
                is NavigationEvent.NavigateUp -> navController.navigateUp()
                is NavigationEvent.NavigateTo ->
                    navController.navigate(
                        event.route,
                        event.builder
                    )
            }
        }
    }
}

