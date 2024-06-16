package com.kontvip.list.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.kontvip.list.BaseComposeTest
import com.kontvip.list.domain.core.ListScreenUiState
import kotlinx.coroutines.flow.StateFlow
import androidx.navigation.testing.TestNavHostController
import com.kontvip.list.presentation.model.BookUi
import com.kontvip.list.presentation.model.FailUiState
import com.kontvip.list.presentation.model.ListUiState
import com.kontvip.list.presentation.model.LoadingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ListScreenKtTest : BaseComposeTest() {

    private lateinit var fakeViewModel: ListViewModel
    private lateinit var testNavController: NavHostController

    @Before
    fun setup() {
        fakeViewModel = FakeListViewModel()
        testNavController = TestNavHostController(context)
    }

    @Test
    fun listScreenDisplaysTopAppBar() {
        val fakeViewModel = FakeListViewModel()

        composeTestRule.setContent {
            ListScreen(navController = testNavController, viewModel = fakeViewModel)
        }

        composeTestRule.onNodeWithText(string(com.kontvip.common.R.string.app_name))
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun listScreenDisplaysListUiState() {
        val fakeViewModel = FakeListViewModel()
        val books = listOf(
            BookUi("3", "Title 1", "Description 1", "url1"),
            BookUi("5", "Title 2", "Description 2", "url2")
        )
        val uiState = ListUiState(books)

        fakeViewModel.listScreenUiStateFlowToReturn = uiState

        composeTestRule.setContent {
            ListScreen(navController = testNavController, viewModel = fakeViewModel)
        }

        assertTrue(fakeViewModel.wasListScreenUiStateFlowCalled)

        composeTestRule.onNodeWithText("Title 1")
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        assertEquals(1, fakeViewModel.selectBookCalledCount)
        assertEquals("3", fakeViewModel.selectedBookId)

        composeTestRule.onNodeWithText("Title 2")
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        assertEquals(2, fakeViewModel.selectBookCalledCount)
        assertEquals("5", fakeViewModel.selectedBookId)
    }

    @Test
    fun listScreenDisplaysLoadingUiState() {
        val fakeViewModel = FakeListViewModel()
        val uiState = LoadingUiState

        fakeViewModel.listScreenUiStateFlowToReturn = uiState

        composeTestRule.setContent {
            ListScreen(navController = testNavController, viewModel = fakeViewModel)
        }

        assertTrue(fakeViewModel.wasListScreenUiStateFlowCalled)

        composeTestRule.onNodeWithTag("CenteredCircularProgressIndicator")
            .assertExists()
            .assertIsDisplayed()
            .performClick()
    }

    @Test
    fun listScreenDisplaysFailUiState() {
        val fakeViewModel = FakeListViewModel()
        val uiState = FailUiState("Fail!")

        fakeViewModel.listScreenUiStateFlowToReturn = uiState

        composeTestRule.setContent {
            ListScreen(navController = testNavController, viewModel = fakeViewModel)
        }

        assertTrue(fakeViewModel.wasListScreenUiStateFlowCalled)

        composeTestRule.onNodeWithText("Fail!")
            .assertExists()
            .assertIsDisplayed()
            .performClick()
    }

    private class FakeListViewModel : ListViewModel {
        var loadBooksDataCalledCount = 0
        var selectBookCalledCount = 0
        var selectedBookId : String? = null
        var wasListScreenUiStateFlowCalled: Boolean = false

        var listScreenUiStateFlowToReturn: ListScreenUiState = LoadingUiState


        override fun loadBooksData() {
            loadBooksDataCalledCount++
        }

        override fun listScreenUiStateFlow(): StateFlow<ListScreenUiState> {
            wasListScreenUiStateFlowCalled = true
            return MutableStateFlow(listScreenUiStateFlowToReturn)
        }

        override fun selectBook(navController: NavController, bookId: String) {
            selectBookCalledCount++
            selectedBookId = bookId
        }
    }
}