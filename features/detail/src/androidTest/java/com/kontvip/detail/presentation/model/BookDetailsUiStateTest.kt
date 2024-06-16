package com.kontvip.detail.presentation.model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.kontvip.detail.BaseComposeTest
import org.junit.Test

class BookDetailsUiStateTest : BaseComposeTest() {
    @Test
    fun uiDisplay_contentDisplayedCorrectly() {
        val uiState = BookDetailsUiState(
            title = "Test Title",
            description = "Test Description",
            author = "Test Author",
            releaseDate = "1980-01-01",
            imageUrl = "https://test.com"
        )

        composeTestRule.setContent {
            uiState.UiDisplay()
        }

        composeTestRule.onNodeWithContentDescription(string(com.kontvip.common.R.string.book_cover))
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Test Title")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("1980-01-01")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Test Description")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Test Author")
            .assertExists()
            .assertIsDisplayed()
    }
}