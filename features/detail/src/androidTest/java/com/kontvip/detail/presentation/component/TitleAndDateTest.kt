package com.kontvip.detail.presentation.component

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.kontvip.detail.BaseComposeTest
import org.junit.Test

class TitleAndDateTest : BaseComposeTest() {
    @Test
    fun titleAndDate_displayedCorrectly() {
        val title = "Test Title"
        val date = "1980-01-01"

        composeTestRule.setContent {
            TitleAndDate(title = title, date = date)
        }

        composeTestRule.onNodeWithText(title).assertExists().isDisplayed()
        composeTestRule.onNodeWithText(date).assertExists().isDisplayed()
    }
}