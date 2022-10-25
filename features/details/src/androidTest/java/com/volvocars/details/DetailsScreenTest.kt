package com.volvocars.details

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.volvocars.details.presentation.DetailsScreen
import com.volvocars.details.presentation.navigation.DetailsNavigationModel
import com.volvocars.details.presentation.views.TAG_DETAILS
import com.volvocars.details.presentation.views.TAG_TOOLBAR
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailsScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            DetailsScreen(
                DetailsNavigationModel(
                    "cityName",
                    "weather",
                    "temperature",
                    "temperatureMin",
                    "temperatureMax",
                    "pressure",
                    "humidity",
                    "windSpeed",
                )
            )
        }
    }

    @Test
    fun toolbarExist() {
        composeTestRule.onNodeWithTag(TAG_TOOLBAR)
            .assertExists()
    }

    @Test
    fun weatherTetExist() {
        composeTestRule.onNodeWithTag(TAG_DETAILS)
            .assert(
                hasAnyDescendant(hasText("weather")) and
                        hasAnyDescendant(hasText("temperature")) and
                        hasAnyDescendant(hasText("temperatureMin")) and
                        hasAnyDescendant(hasText("temperatureMax")) and
                        hasAnyDescendant(hasText("pressure")) and
                        hasAnyDescendant(hasText("humidity")) and
                        hasAnyDescendant(hasText("windSpeed"))
            )
    }

}
