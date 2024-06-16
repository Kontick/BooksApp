package com.kontvip.list.presentation.core

import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.domain.model.ListResult
import com.kontvip.list.presentation.model.FailUiState
import com.kontvip.list.presentation.model.ListUiState
import org.junit.Assert.*
import org.junit.Test

class DefaultBooksListUiFactoryTest {

    @Test
    fun `construct should map Success result to ListScreenUiState`() {
        val expectedUiState = ListUiState(emptyList())
        val successMapper = object : ListResult.Success.Mapper<ListScreenUiState> {
            override fun map(books: List<DomainListBook>): ListScreenUiState {
                return expectedUiState
            }
        }
        val failMapper = object : ListResult.Fail.Mapper<ListScreenUiState> {
            override fun map(errorMessage: String): ListScreenUiState {
                return FailUiState(errorMessage)
            }
        }
        val factory = DefaultBooksListUiFactory(successMapper, failMapper)
        val successResult = ListResult.Success(emptyList())

        val actualUiState = factory.construct(successResult)

        assertEquals(expectedUiState, actualUiState)
    }

    @Test
    fun `construct should map Fail result to ListScreenUiState`() {
        val expectedErrorMessage = "An error occurred"
        val expectedUiState = ListUiState(emptyList())

        val successMapper = object : ListResult.Success.Mapper<ListScreenUiState> {
            override fun map(books: List<DomainListBook>): ListScreenUiState {
                return ListUiState(emptyList())
            }
        }
        val failMapper = object : ListResult.Fail.Mapper<ListScreenUiState> {
            override fun map(errorMessage: String): ListScreenUiState {
                return expectedUiState
            }
        }
        val factory = DefaultBooksListUiFactory(successMapper, failMapper)
        val failResult = ListResult.Fail(expectedErrorMessage, false)

        val actualUiState = factory.construct(failResult)

        assertEquals(expectedUiState, actualUiState)
    }
}