package com.kontvip.list.presentation.core

import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.presentation.model.BookUi
import com.kontvip.list.presentation.model.ListUiState
import org.junit.Assert.*
import org.junit.Test

class SuccessResultToListScreenUiStateTest {

    @Test
    fun `map should create ListUiState`() {
        val fakeDomainToBookUiMapper = object : DomainListBook.Mapper<BookUi> {
            override fun map(
                id: String, title: String, description: String, imageUrl: String
            ): BookUi {
                return BookUi(id, title, description, imageUrl)
            }
        }

        val actual = SuccessResultToListScreenUiState(fakeDomainToBookUiMapper).map(
            listOf(DomainListBook("1", "t", "d", "i"))
        )
        val expected = ListUiState(listOf(BookUi("1", "t", "d", "i")))
        assertEquals(expected, actual)
    }

}