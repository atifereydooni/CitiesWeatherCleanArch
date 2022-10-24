package com.volvocars.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object DetailsDestination : NavigationDestination {

    object Params {
        const val CITY_NAME = "city_name"
        const val WEATHER = "weather"
        const val TEMPERATURE = "temperature"
        const val TEMPERATURE_MIN = "temperature_min"
        const val TEMPERATURE_MAX = "temperature_max"
        const val PRESSURE = "pressure"
        const val HUMIDITY = "humidity"
        const val WIND_SPEED = "windSpeed"
    }

    const val DETAILS_ROUTE = "details"
    private const val DETAILS_ROUTE_WITH_KEY =
        "$DETAILS_ROUTE/{${Params.CITY_NAME}}/{${Params.WEATHER}}/{${Params.TEMPERATURE}}/{${Params.TEMPERATURE_MIN}}/{${Params.TEMPERATURE_MAX}}/{${Params.PRESSURE}}/{${Params.HUMIDITY}}/{${Params.WIND_SPEED}}"

    override val route = DETAILS_ROUTE_WITH_KEY
    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(Params.CITY_NAME) { type = NavType.StringType },
            navArgument(Params.WEATHER) { type = NavType.StringType },
            navArgument(Params.TEMPERATURE) { type = NavType.StringType },
            navArgument(Params.TEMPERATURE_MIN) { type = NavType.StringType },
            navArgument(Params.TEMPERATURE_MAX) { type = NavType.StringType },
            navArgument(Params.PRESSURE) { type = NavType.StringType },
            navArgument(Params.HUMIDITY) { type = NavType.StringType },
            navArgument(Params.WIND_SPEED) { type = NavType.StringType }
        )

    fun createDetailsRoute(
        cityName: String,
        weather: String,
        temperature: String,
        temperatureMin: String,
        temperatureMax: String,
        pressure: String,
        humidity: String,
        windSpeed: String,
    ) = DETAILS_ROUTE +
            "/${cityName}" +
            "/${weather}" +
            "/${temperature}" +
            "/${temperatureMin}" +
            "/${temperatureMax}" +
            "/${pressure}" +
            "/${humidity}" +
            "/${windSpeed}"

}
