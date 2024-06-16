package com.kontvip.detail.presentation

import com.kontvip.common.core.DispatcherList
import com.kontvip.detail.domain.FetchBookDetailsUseCase
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.model.LoadingUiState
import com.kontvip.detail.presentation.model.NoBookFoundUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var fakeFetchBookDetailsUseCase: FetchBookDetailsUseCase
    private lateinit var fakeDispatcherList: DispatcherList
    private lateinit var viewModel: DetailViewModel
    private var wasUiDispatcherCalled: Boolean = false
    private var wasIoDispatcherCalled: Boolean = false
    private var fetchBookDetailsUseCaseInvokeCalled: Boolean = false

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        fakeFetchBookDetailsUseCase = object : FetchBookDetailsUseCase {
            override suspend fun invoke(bookId: String): DetailScreenUiState {
                fetchBookDetailsUseCaseInvokeCalled = true
                return NoBookFoundUiState
            }
        }
        fakeDispatcherList = object : DispatcherList {
            override fun ui(): CoroutineDispatcher {
                wasUiDispatcherCalled = true
                return UnconfinedTestDispatcher()
            }

            override fun io(): CoroutineDispatcher {
                wasIoDispatcherCalled = true
                return UnconfinedTestDispatcher()
            }
        }
        wasUiDispatcherCalled = false
        wasIoDispatcherCalled = false
        fetchBookDetailsUseCaseInvokeCalled = false
        viewModel = DetailViewModel.Default(fakeFetchBookDetailsUseCase, fakeDispatcherList)
    }

    @Test
    fun `initial state of detailScreenUiStateFlow should be LoadingUiState`() {
        assertEquals(LoadingUiState, viewModel.detailScreenUiStateFlow().value)
    }

    @Test
    fun `loadBookWithId should invoke FetchBookDetailsUseCase with io dispatcher and change detailScreenUiStateFlow value`() {
        viewModel.loadBookWithId("123")

        val actualUiState = viewModel.detailScreenUiStateFlow().value

        assertTrue(wasIoDispatcherCalled)
        assertFalse(wasUiDispatcherCalled)
        assertTrue(fetchBookDetailsUseCaseInvokeCalled)

        assertEquals(NoBookFoundUiState, actualUiState)
    }
}