package com.kontvip.common.data.cache.model

import org.junit.Assert.*
import org.junit.Test

class CacheBookTest {

    @Test
    fun `map should transform CacheBook into TestMapperResult`() {
        val cacheBook = CacheBook(
            id = "1",
            title = "Test Title",
            description = "Test Description",
            author = "Test Author",
            releaseDate = "1980-01-01",
            dateInMillis = 0,
            imageUrl = "https://test.com"
        )

        val actual = cacheBook.map(TestMapper())

        val expected = TestMapperResult(
            id = "1",
            title = "Test Title",
            description = "Test Description",
            author = "Test Author",
            releaseDate = "1980-01-01",
            imageUrl = "https://test.com"
        )
        assertEquals(expected, actual)
    }

    private class TestMapper : CacheBook.Mapper<TestMapperResult> {
        override fun map(
            id: String,
            title: String,
            description: String,
            author: String,
            releaseDate: String,
            imageUrl: String
        ): TestMapperResult {
            return TestMapperResult(id, title, description, author, releaseDate, imageUrl)
        }
    }

    private data class TestMapperResult(
        val id: String,
        val title: String,
        val description: String,
        val author: String,
        val releaseDate: String,
        val imageUrl: String
    )

}