package com.kontvip.detail.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import com.kontvip.detail.BaseComposeTest
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.model.LoadingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Assert.*
import com.kontvip.common.R
import com.kontvip.detail.presentation.model.BookDetailsUiState
import com.kontvip.detail.presentation.model.NoBookFoundUiState
import org.junit.Test

class DetailScreenKtTest : BaseComposeTest() {

    @Test
    fun detailScreen_whenLoading_contentDisplayedCorrectly() {
        var wasLoadBookWithIdCalled = false
        var detailScreenUiStateFlowCalled = false
        var passedBookId: String? = null
        composeTestRule.setContent {
            DetailScreen(
                navController = TestNavHostController(context),
                bookId = "123",
                viewModel = object : DetailViewModel {
                    override fun loadBookWithId(bookId: String) {
                        passedBookId = bookId
                        wasLoadBookWithIdCalled = true
                    }

                    override fun detailScreenUiStateFlow(): StateFlow<DetailScreenUiState> {
                        detailScreenUiStateFlowCalled = true
                        return MutableStateFlow(LoadingUiState)
                    }
                })
        }

        composeTestRule.onNodeWithText(string(R.string.app_name))
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("CenteredCircularProgressIndicator")
            .assertExists()
            .assertIsDisplayed()

        assertEquals("123", passedBookId)
        assertTrue(wasLoadBookWithIdCalled)
        assertTrue(detailScreenUiStateFlowCalled)
    }

    @Test
    fun detailScreen_whenBookDetailsAvailable_contentDisplayedCorrectly() {
        var wasLoadBookWithIdCalled = false
        var detailScreenUiStateFlowCalled = false
        var passedBookId: String? = null
        composeTestRule.setContent {
            DetailScreen(
                navController = TestNavHostController(context),
                bookId = "123",
                viewModel = object : DetailViewModel {
                    override fun loadBookWithId(bookId: String) {
                        passedBookId = bookId
                        wasLoadBookWithIdCalled = true
                    }

                    override fun detailScreenUiStateFlow(): StateFlow<DetailScreenUiState> {
                        detailScreenUiStateFlowCalled = true
                        return MutableStateFlow(
                            BookDetailsUiState(
                                title = "Test Title",
                                description = "Test Description",
                                author = "Test Author",
                                releaseDate = "1980-01-01",
                                imageUrl = "https://test.com"
                            )
                        )
                    }
                })
        }

        composeTestRule.onNodeWithContentDescription(string(R.string.book_cover))
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

        assertEquals("123", passedBookId)
        assertTrue(wasLoadBookWithIdCalled)
        assertTrue(detailScreenUiStateFlowCalled)
    }

    @Test
    fun detailScreen_whenNoBookFound_contentDisplayedCorrectly() {
        var wasLoadBookWithIdCalled = false
        var detailScreenUiStateFlowCalled = false
        var passedBookId: String? = null
        composeTestRule.setContent {
            DetailScreen(
                navController = TestNavHostController(context),
                bookId = "123",
                viewModel = object : DetailViewModel {
                    override fun loadBookWithId(bookId: String) {
                        passedBookId = bookId
                        wasLoadBookWithIdCalled = true
                    }

                    override fun detailScreenUiStateFlow(): StateFlow<DetailScreenUiState> {
                        detailScreenUiStateFlowCalled = true
                        return MutableStateFlow(NoBookFoundUiState)
                    }
                })
        }

        composeTestRule.onNodeWithContentDescription(string(R.string.no_book_found_message_image_description))
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(string(R.string.no_book_found_message))
            .assertExists()
            .assertIsDisplayed()

        assertEquals("123", passedBookId)
        assertTrue(wasLoadBookWithIdCalled)
        assertTrue(detailScreenUiStateFlowCalled)
    }

}