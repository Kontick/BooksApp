package com.kontvip.detail.presentation.model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.kontvip.detail.BaseComposeTest
import org.junit.Test

class NoBookFoundUiStateTest : BaseComposeTest() {
    @Test
    fun uiDisplay_contentDisplayedCorrectly() {
        composeTestRule.setContent {
            NoBookFoundUiState.UiDisplay()
        }

        composeTestRule.onNodeWithContentDescription(string(com.kontvip.common.R.string.no_book_found_message_image_description))
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(string(com.kontvip.common.R.string.no_book_found_message))
            .assertExists()
            .assertIsDisplayed()
    }
}