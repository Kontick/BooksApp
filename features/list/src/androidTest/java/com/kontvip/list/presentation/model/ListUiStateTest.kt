package com.kontvip.list.presentation.model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.kontvip.list.BaseComposeTest
import org.junit.Assert.*
import org.junit.Test

class ListUiStateTest : BaseComposeTest() {
    @Test
    fun uiDisplayShowsListOfBooks() {
        val books = listOf(
            BookUi("1", "Title 1", "Description 1", "url1"),
            BookUi("2", "Title 2", "Description 2", "url2")
        )
        val listUiState = ListUiState(books)

        composeTestRule.setContent {
            listUiState.UiDisplay(onBookSelected = {})
        }

        composeTestRule.onNodeWithText("Title 1").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithText("Title 2").assertExists().assertIsDisplayed()
    }

    @Test
    fun canBeDisplayedReturnsTrueWhenBooksNotEmpty() {
        val books = listOf(
            BookUi("1", "Title 1", "Description 1", "url1")
        )
        val listUiState = ListUiState(books)
        val result = listUiState.canBeDisplayed()

        assertTrue(result)
    }

    @Test
    fun canBeDisplayedReturnsFalseWhenBooksEmpty() {
        val books = emptyList<BookUi>()
        val listUiState = ListUiState(books)
        val result = listUiState.canBeDisplayed()

        assertFalse(result)
    }
}