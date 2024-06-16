package com.kontvip.list.data

import com.kontvip.list.domain.model.DomainListBook
import org.junit.Assert.*
import org.junit.Test

class CacheToDomainListBookMapperTest {

    @Test
    fun `map should transform CacheBook into DomainListBook`() {
        val id = "1"
        val title = "Test title"
        val description = "Test description."
        val author = "Author"
        val releaseDate = "1980-01-01"
        val imageUrl = "https://test.com"

        val actual = CacheToDomainListBookMapper().map(id, title, description, author, releaseDate, imageUrl)
        val expected = DomainListBook(id, title, description, imageUrl)
        assertEquals(expected, actual)
    }
}