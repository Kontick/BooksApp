package com.kontvip.list.data

import com.kontvip.common.core.StringProvider
import com.kontvip.list.data.date.BooksAppDateParser
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CloudToCacheBookMapperTest {
    private lateinit var mapper: CloudToCacheBookMapper

    @Before
    fun setup() {
        mapper = CloudToCacheBookMapper(
            setOf(
                BooksAppDateParser.RegularDateParser,
                BooksAppDateParser.YearOnlyDateParser,
                BooksAppDateParser.YearsBCEDateParser
            ),
            object : StringProvider {
                override fun string(stringRes: Int): String = "[No Title]"
            }
        )
    }

    @Test
    fun `map should return CacheBook with valid input`() {
        val id = "123"
        val title = "Test Title"
        val description = "Test description"
        val author = "Test author"
        val releaseDate = "2024"
        val imageUrl = "https://test.com"

        val result = mapper.map(id, title, description, author, releaseDate, imageUrl)

        assertEquals(id, result.id)
        assertEquals(title, result.title)
        assertEquals(description, result.description)
        assertEquals(author, result.author)
        assertEquals(releaseDate, result.releaseDate)
        assertEquals(imageUrl, result.imageUrl)
    }

    @Test
    fun `map should use default title when title is blank`() {
        val id = "123"
        val title = ""
        val description = "Test description"
        val author = "Test author"
        val releaseDate = "2024"
        val imageUrl = "https://test.com"

        val result = mapper.map(id, title, description, author, releaseDate, imageUrl)

        assertEquals("[No Title]", result.title)
    }

    @Test
    fun `map should not change releaseDate if it has invalid format`() {
        val id = "123"
        val title = "Test Title"
        val description = "Test description"
        val author = "Test author"
        val releaseDate = "2/2/2/2/2"
        val imageUrl = "https://test.com"

        val result = mapper.map(id, title, description, author, releaseDate, imageUrl)

        assertEquals("2/2/2/2/2", result.releaseDate)
    }
}