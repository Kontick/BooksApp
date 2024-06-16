package com.kontvip.detail.presentation.core

import com.kontvip.detail.presentation.model.BookDetailsUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class DomainToBookDetailsUiStateMapperTest {
    @Test
    fun `map create BookDetailsUiState`() {
        val title = "Test title"
        val description = "Test description."
        val author = "Author"
        val releaseDate = "1980-01-01"
        val imageUrl = "https://test.com"

        val actual = DomainToBookDetailsUiStateMapper().map(title, description, author, releaseDate, imageUrl)
        val expected = BookDetailsUiState(title, description, author, releaseDate, imageUrl)
        assertEquals(expected, actual)
    }
}