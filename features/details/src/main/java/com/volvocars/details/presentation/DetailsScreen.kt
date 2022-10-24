package com.volvocars.details.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.volvocars.details.presentation.navigation.DetailsNavigationModel
import com.volvocars.details.presentation.views.DetailsView

@Composable
fun DetailsScreen(navigationModel: DetailsNavigationModel? = null) {

    Scaffold {
        DetailsView(navigationModel)
    }

}

