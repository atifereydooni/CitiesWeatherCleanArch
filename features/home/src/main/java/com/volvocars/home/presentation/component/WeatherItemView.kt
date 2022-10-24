package com.volvocars.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.volvocars.home.presentation.view.WeatherItemModel
import com.volvocars.home.presentation.view.WeatherItemState
import kotlin.math.roundToInt

@Composable
fun WeatherItemView(
    itemData: WeatherItemModel,
    onItemClickListener: ((WeatherItemModel) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {
                    onItemClickListener?.invoke(itemData)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = itemData.name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .wrapContentWidth(),
                    color = MaterialTheme.colors.onSurface,
                )
                when (itemData.state) {
                    WeatherItemState.Loading -> CircularProgressIndicator()
                    WeatherItemState.Success -> {
                        Text(
                            text = "${
                                (itemData.data?.main?.temp?.minus(273.15)
                                    ?.roundToInt())
                            } °C",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .wrapContentWidth(),
                            color = MaterialTheme.colors.onSurface,
                        )
                    }
                    WeatherItemState.Failed -> Text(text = "Retry")
                }
            }
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                painter = rememberImagePainter(itemData.icon),
                contentDescription = null
            )
        }
    }
}
