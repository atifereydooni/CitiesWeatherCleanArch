package com.volvocars.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.volvocars.home.presentation.component.CityListView
import com.volvocars.home.presentation.component.TAG_LIST
import com.volvocars.home.presentation.component.TAG_TOOLBAR
import com.volvocars.home.presentation.view.WeatherItemModel
import com.volvocars.home.presentation.view.WeatherItemState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun toolbarExist() {
        composeTestRule.setContent {
            CityListView(listOf())
        }
        composeTestRule.onNodeWithTag(TAG_TOOLBAR)
            .assertExists()
    }

    @Test
    fun listIsEmpty() {
        composeTestRule.setContent {
            CityListView(listOf())
        }
        composeTestRule.onNodeWithTag(TAG_LIST)
            .onChildren()
            .assertCountEquals(0)
    }

    @Test
    fun listHas6Items() {
        composeTestRule.setContent {
            CityListView(getMockList())
        }
        composeTestRule.onNodeWithTag(TAG_LIST)
            .onChildren()
            .assertCountEquals(6)
    }

    @Test
    fun firstItemName() {
        composeTestRule.setContent {
            CityListView(getMockList())
        }
        composeTestRule.onNodeWithTag(TAG_LIST)
            .onChildren()
            .onFirst()
            .assert(hasTestTag("Gothenburg"))
    }

    @Test
    fun lastItemName() {
        composeTestRule.setContent {
            CityListView(getMockList())
        }
        composeTestRule.onNodeWithTag(TAG_LIST)
            .onChildren()
            .onLast()
            .assert(hasTestTag("Berlin"))
    }

    private fun getMockList(): List<WeatherItemModel> = listOf(
        WeatherItemModel("Gothenburg", WeatherItemState.Loading),
        WeatherItemModel("Stockholm", WeatherItemState.Loading),
        WeatherItemModel("Mountain View", WeatherItemState.Success),
        WeatherItemModel("London", WeatherItemState.Loading),
        WeatherItemModel("New York", WeatherItemState.Loading),
        WeatherItemModel("Berlin", WeatherItemState.Success)
    )

}
