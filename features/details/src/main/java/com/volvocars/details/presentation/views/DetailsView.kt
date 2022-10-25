package com.volvocars.details.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.volvocars.details.presentation.navigation.DetailsNavigationModel


@Composable
fun DetailsView(navigationModel: DetailsNavigationModel?) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag(TAG_TOOLBAR),
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(navigationModel?.cityName ?: "NON") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(8.dp).testTag(TAG_DETAILS)) {
            Row {
                Text(text = "Weather: ")
                Text(text = navigationModel?.weather ?: "-")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "Temperature: ")
                Text(text = navigationModel?.temperature ?: "-")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "Minimum Temperature: ")
                Text(text = navigationModel?.temperatureMin ?: "-")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "Maximum Temperature: ")
                Text(text = navigationModel?.temperatureMax ?: "-")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "Pressure: ")
                Text(text = navigationModel?.pressure ?: "-")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "Humidity: ")
                Text(text = navigationModel?.humidity ?: "-")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "Wind Speed: ")
                Text(text = navigationModel?.windSpeed ?: "-")
            }
        }
    }
}

const val TAG_TOOLBAR = "TagToolbar"
const val TAG_DETAILS = "TagDetails"
