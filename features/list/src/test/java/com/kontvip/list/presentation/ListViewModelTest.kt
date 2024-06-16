package com.kontvip.list.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.kontvip.common.core.DispatcherList
import com.kontvip.common.navigation.DetailRouteProvider
import com.kontvip.common.navigation.Route
import com.kontvip.common.navigation.navigateIfResumed
import com.kontvip.list.domain.FetchBooksUseCase
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.presentation.model.ListUiState
import com.kontvip.list.presentation.model.LoadingUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {
    @Test
    fun `loadBooksData invokes use case and updates state flow`() = runTest {
        val fetchBooksUseCase = FakeFetchBooksUseCase()
        val fakeDispatchers = FakeDispatcherList()
        val viewModel = ListViewModel.Default(
            fetchBooksUseCase,
            fakeDispatchers,
            FakeDetailRouteProvider()
        )

        viewModel.loadBooksData()
        fetchBooksUseCase.onResultBlock?.invoke(ListUiState(emptyList()))

        assertTrue(fakeDispatchers.wasIoCalled)
        assertFalse(fakeDispatchers.wasUiCalled)

        val actualUiState = viewModel.listScreenUiStateFlow().value
        val expectedUiState = ListUiState(emptyList())

        assertEquals(expectedUiState, actualUiState)
    }

    @Test
    fun `default value of listScreenUiStateFlow should be LoadingUiState`() = runTest {
        val viewModel = ListViewModel.Default(
            FakeFetchBooksUseCase(),
            FakeDispatcherList(),
            FakeDetailRouteProvider()
        )
        val actualUiState = viewModel.listScreenUiStateFlow().value
        val expectedUiState = LoadingUiState

        assertEquals(expectedUiState, actualUiState)
    }

    @Test
    fun `selectBook navigates to correct route`() = runTest {
        val fakeRouteProvider = FakeDetailRouteProvider()
        val viewModel = ListViewModel.Default(
            FakeFetchBooksUseCase(),
            FakeDispatcherList(),
            fakeRouteProvider
        )

        val mockNavController: NavHostController = mock()

        viewModel.selectBook(mockNavController, "test_book_id")

        assertTrue(fakeRouteProvider.wasRouteCalled)

        verify(mockNavController).navigateIfResumed(object : Route {
            @Composable
            override fun Content(navController: NavController) = Unit
        })
    }

    private class FakeFetchBooksUseCase : FetchBooksUseCase {
        var onResultBlock: ((ListScreenUiState) -> Unit)? = null

        override suspend fun invoke(onResultBlock: (ListScreenUiState) -> Unit) {
            this.onResultBlock = onResultBlock
        }
    }

    private class FakeDispatcherList : DispatcherList {
        var wasIoCalled = false
        var wasUiCalled = false

        override fun io(): CoroutineDispatcher {
            wasIoCalled = true
            return UnconfinedTestDispatcher()
        }

        override fun ui(): CoroutineDispatcher {
            wasUiCalled = true
            return UnconfinedTestDispatcher()
        }
    }

    private class FakeDetailRouteProvider : DetailRouteProvider {
        var wasRouteCalled = false
        override fun route(id: String): Route {
            wasRouteCalled = true
            return object : Route {
                @Composable
                override fun Content(navController: NavController) = Unit
            }
        }
    }
}