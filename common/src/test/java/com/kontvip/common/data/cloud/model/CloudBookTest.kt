package com.kontvip.common.data.cloud.model

import org.junit.Assert.*
import org.junit.Test

class CloudBookTest {

    @Test
    fun `isValid returns true when id is not null or blank`() {
        val validCloudBook = CloudBook(id = "1")
        assertTrue(validCloudBook.isValid())
    }

    @Test
    fun `isValid returns false when id is null or blank`() {
        val invalidCloudBook1 = CloudBook(id = null)
        val invalidCloudBook2 = CloudBook(id = "")
        assertFalse(invalidCloudBook1.isValid())
        assertFalse(invalidCloudBook2.isValid())
    }

    @Test
    fun `map should transform CloudBook into TestMapperResult`() {
        val cloudBook = CloudBook(
            id = "1",
            title = "Test Title",
            description = "Test Description",
            author = "Test Author",
            releaseDate = "1980-01-01",
            imageUrl = "https://test.com"
        )

        val actual = cloudBook.map(TestMapper())

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

    private class TestMapper : CloudBook.Mapper<TestMapperResult> {
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