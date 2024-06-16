package com.kontvip.detail.data

import com.kontvip.detail.domain.model.DomainBooksDetail
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CacheToDomainBooksDetailMapperTest {

    @Test
    fun `map should transform CacheBook into DomainBooksDetail`() {
        val id = "1"
        val title = "Test title"
        val description = "Test description."
        val author = "Author"
        val releaseDate = "1980-01-01"
        val imageUrl = "https://test.com"

        val actual = CacheToDomainBooksDetailMapper().map(id, title, description, author, releaseDate, imageUrl)
        val expected = DomainBooksDetail(title, description, author, releaseDate, imageUrl)
        assertEquals(expected, actual)
    }
}