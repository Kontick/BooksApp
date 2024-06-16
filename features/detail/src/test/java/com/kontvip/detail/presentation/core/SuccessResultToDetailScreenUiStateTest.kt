package com.kontvip.detail.presentation.core

import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.domain.model.DomainBooksDetail
import com.kontvip.detail.presentation.model.BookDetailsUiState
import org.junit.Assert.*
import org.junit.Test

class SuccessResultToDetailScreenUiStateTest {
    @Test
    fun `map create NoBookFoundUiState`() {
        val fakeDomainToBookDetailsMapper = object : DomainBooksDetail.Mapper<DetailScreenUiState> {
            override fun map(
                title: String, description: String, author: String,
                releaseDate: String, imageUrl: String
            ): DetailScreenUiState {
                return BookDetailsUiState(title, description, author, releaseDate, imageUrl)
            }
        }

        val mapper = SuccessResultToDetailScreenUiState(fakeDomainToBookDetailsMapper)

        val actual = mapper.map(DomainBooksDetail("t", "d", "a", "r", "i"))
        val expected = BookDetailsUiState("t", "d", "a", "r", "i")
        assertEquals(expected, actual)
    }
}