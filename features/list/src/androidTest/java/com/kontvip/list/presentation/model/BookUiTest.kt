package com.kontvip.list.presentation.model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.kontvip.list.BaseComposeTest
import org.junit.Assert.*
import org.junit.Test

class BookUiTest : BaseComposeTest() {
    @Test
    fun bookUi_displaysBookCoverAndBookDetails() {
        val bookUi = BookUi(
            id = "1",
            title = "Test Title",
            description = "Test description.",
            imageUrl = "https://test.com"
        )

        composeTestRule.setContent {
            bookUi.UiDisplay(onBookSelected = {})
        }

        composeTestRule.onNodeWithText("Test Title")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Test description.")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun bookUi_invokesOnBookSelected_whenClicked() {
        val bookUi = BookUi(
            id = "1",
            title = "Test Title",
            description = "Test description.",
            imageUrl = "https://test.com"
        )
        var selectedBookId: String? = null

        composeTestRule.setContent {
            bookUi.UiDisplay(onBookSelected = { bookId ->
                selectedBookId = bookId
            })
        }

        composeTestRule.onNodeWithText("Test Title")
            .performClick()

        assertEquals("1", selectedBookId)
    }
}