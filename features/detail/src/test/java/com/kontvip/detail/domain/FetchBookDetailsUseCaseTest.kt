package com.kontvip.detail.domain

import com.kontvip.common.core.DispatcherList
import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.model.NoBookFoundUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FetchBookDetailsUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke should run on io dispatcher and pass expected values to repository and factory`() {
        var actualBookIdInRepository: String? = null
        val fakeRepository = object : BookDetailsRepository {
            override suspend fun findBookWithId(bookId: String): DetailResult {
                actualBookIdInRepository = bookId
                return DetailResult.NoBookFound
            }
        }
        var actualDetailResultInFactory: DetailResult? = null
        val fakeFactory = object : BooksDetailUiFactory {
            override fun construct(detailResult: DetailResult): DetailScreenUiState {
                actualDetailResultInFactory = detailResult
                return NoBookFoundUiState
            }
        }
        var wasUiCalled = false
        var wasIoCalled = false
        val fakeDispatcherList = object : DispatcherList {
            override fun ui(): CoroutineDispatcher {
                wasUiCalled = true
                return UnconfinedTestDispatcher()
            }

            override fun io(): CoroutineDispatcher {
                wasIoCalled = true
                return UnconfinedTestDispatcher()
            }
        }

        val fetchBookDetailsUseCase = FetchBookDetailsUseCase.Default(
            fakeRepository, fakeFactory, fakeDispatcherList
        )

        runBlocking {
            val actualUiState = fetchBookDetailsUseCase.invoke("123")

            assertEquals("123", actualBookIdInRepository)
            assertEquals(DetailResult.NoBookFound, actualDetailResultInFactory)
            assertTrue(wasIoCalled)
            assertFalse(wasUiCalled)
            assertEquals(NoBookFoundUiState, actualUiState)
        }
    }
}