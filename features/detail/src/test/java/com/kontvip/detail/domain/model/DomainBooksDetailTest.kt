package com.kontvip.detail.domain.model

import org.junit.Assert.*
import org.junit.Test

class DomainBooksDetailTest {

    @Test
    fun `map should transform DomainBooksDetail into TestMapperResult`() {
        val cacheBook = DomainBooksDetail(
            title = "Test Title",
            description = "Test Description",
            author = "Test Author",
            releaseDate = "1980-01-01",
            imageUrl = "https://test.com"
        )

        val actual = cacheBook.map(TestMapper())

        val expected = TestMapperResult(
            title = "Test Title",
            description = "Test Description",
            author = "Test Author",
            releaseDate = "1980-01-01",
            imageUrl = "https://test.com"
        )
        assertEquals(expected, actual)
    }

    private class TestMapper : DomainBooksDetail.Mapper<TestMapperResult> {
        override fun map(
            title: String, description: String, author: String,
            releaseDate: String, imageUrl: String
        ): TestMapperResult {
            return TestMapperResult(title, description, author, releaseDate, imageUrl)
        }
    }

    private data class TestMapperResult(
        val title: String,
        val description: String,
        val author: String,
        val releaseDate: String,
        val imageUrl: String
    )
}