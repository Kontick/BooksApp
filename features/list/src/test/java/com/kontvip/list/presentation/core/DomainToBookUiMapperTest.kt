package com.kontvip.list.presentation.core

import com.kontvip.list.presentation.model.BookUi
import org.junit.Assert.*
import org.junit.Test

class DomainToBookUiMapperTest {

    @Test
    fun `map should create DomainToBookUiMapper`() {
        val title = "Test title"
        val description = "Test description."
        val author = "Author"
        val imageUrl = "https://test.com"

        val actual = DomainToBookUiMapper().map(title, description, author, imageUrl)
        val expected = BookUi(title, description, author, imageUrl)
        assertEquals(expected, actual)
    }

}