package com.kontvip.list.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class DomainListBookTest {

    @Test
    fun `map should transform DomainListBook into TestMapperResult`() {
        val cacheBook = DomainListBook(
            id = "1",
            title = "Test Title",
            description = "Test Description",
            imageUrl = "https://test.com"
        )

        val actual = cacheBook.map(TestMapper())

        val expected = TestMapperResult(
            id = "1",
            title = "Test Title",
            description = "Test Description",
            imageUrl = "https://test.com"
        )
        assertEquals(expected, actual)
    }

    private class TestMapper : DomainListBook.Mapper<TestMapperResult> {
        override fun map(
            id: String, title: String, description: String, imageUrl: String
        ): TestMapperResult {
            return TestMapperResult(id, title, description, imageUrl)
        }
    }

    private data class TestMapperResult(
        val id: String,
        val title: String,
        val description: String,
        val imageUrl: String
    )

}