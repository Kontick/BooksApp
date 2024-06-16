package com.kontvip.detail.presentation.core

import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.domain.model.DomainBooksDetail
import com.kontvip.detail.presentation.model.BookDetailsUiState
import com.kontvip.detail.presentation.model.LoadingUiState
import org.junit.Assert.*
import org.junit.Test

class DefaultBooksDetailUiFactoryTest {

    @Test
    fun `construct success result`() {
        var wasSuccessMapperCalled = false
        val successMapper = object : DetailResult.Success.Mapper<DetailScreenUiState> {
            override fun map(domainBooksDetail: DomainBooksDetail): DetailScreenUiState {
                wasSuccessMapperCalled = true
                return BookDetailsUiState("t", "d", "a", "r", "i")
            }
        }

        var wasNoBooksMapperCalled = false
        val noBooksMapper = object : DetailResult.NoBookFound.Mapper<DetailScreenUiState> {
            override fun map(): DetailScreenUiState {
                wasNoBooksMapperCalled = true
                return LoadingUiState
            }
        }
        val factory = DefaultBooksDetailUiFactory(successMapper, noBooksMapper)

        val actualUiState = factory.construct(
            DetailResult.Success(DomainBooksDetail("t", "d", "a", "r", "i"))
        )

        assertTrue(wasSuccessMapperCalled)
        assertFalse(wasNoBooksMapperCalled)
        assertEquals(
            BookDetailsUiState("t", "d", "a", "r", "i"),
            actualUiState
        )
    }

    @Test
    fun `construct no books result`() {
        var wasSuccessMapperCalled = false
        val successMapper = object : DetailResult.Success.Mapper<DetailScreenUiState> {
            override fun map(domainBooksDetail: DomainBooksDetail): DetailScreenUiState {
                wasSuccessMapperCalled = true
                return BookDetailsUiState("t", "d", "a", "r", "i")
            }
        }

        var wasNoBooksMapperCalled = false
        val noBooksMapper = object : DetailResult.NoBookFound.Mapper<DetailScreenUiState> {
            override fun map(): DetailScreenUiState {
                wasNoBooksMapperCalled = true
                return LoadingUiState
            }
        }
        val factory = DefaultBooksDetailUiFactory(successMapper, noBooksMapper)

        val actualUiState = factory.construct(
            DetailResult.NoBookFound
        )

        assertFalse(wasSuccessMapperCalled)
        assertTrue(wasNoBooksMapperCalled)
        assertEquals(LoadingUiState, actualUiState)
    }
}