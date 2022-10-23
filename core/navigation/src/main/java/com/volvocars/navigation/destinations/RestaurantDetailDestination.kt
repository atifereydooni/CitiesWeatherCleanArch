package com.volvocars.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object RestaurantDetailDestination : NavigationDestination {
    const val RESTAURANT_PARAM = "restaurant"
    const val DETAIL_ROUTE = "detail"
    const val DETAIL_ROUTE_WITH_KEY = "$DETAIL_ROUTE/{$RESTAURANT_PARAM}"

    override val route = DETAIL_ROUTE_WITH_KEY
    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(RESTAURANT_PARAM) { type = NavType.StringType })

    fun createDetailRoute(restaurant: String) = "$DETAIL_ROUTE/$restaurant"
}
