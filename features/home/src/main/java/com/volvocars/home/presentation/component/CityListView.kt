package com.volvocars.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.volvocars.home.presentation.view.WeatherItemModel

@Composable
fun CityListView(
    weathersItems: List<WeatherItemModel>,
    onItemClickListener: (WeatherItemModel) -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag(TAG_TOOLBAR),
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text("City Weather") }
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            modifier = Modifier.fillMaxWidth().testTag(TAG_LIST)
        ) {
            itemsIndexed(weathersItems) { _, item ->
                WeatherItemView(item, onItemClickListener = {
                    onItemClickListener(it)
                })
            }
        }
    }
}

const val TAG_TOOLBAR = "TagToolbar"
const val TAG_LIST = "TagList"
