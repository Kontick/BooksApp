package com.kontvip.detail.data

import com.kontvip.common.data.cache.BooksDao
import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DomainBooksDetail
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultBookDetailsRepositoryTest {

    @Test
    fun `test findBookWithId when book found`() {
        val dao = TestDao(listOf(CacheBook("1", "t", "d", "a", "1", 0L, "h")))

        var isMapperCalled = false
        val mapper = object : CacheBook.Mapper<DomainBooksDetail> {
            override fun map(
                id: String, title: String, description: String,
                author: String, releaseDate: String, imageUrl: String
            ): DomainBooksDetail {
                isMapperCalled = true
                return DomainBooksDetail(title, description, author, releaseDate, imageUrl)
            }
        }

        val repository = DefaultBookDetailsRepository(dao, mapper)

        runBlocking {
            val actualDomainBook = repository.findBookWithId("1")

            assertTrue(isMapperCalled)
            assertEquals(
                DetailResult.Success(DomainBooksDetail( "t", "d", "a", "1", "h")),
                actualDomainBook
            )
        }
    }

    @Test
    fun `test findBookWithId when book not found`() {
        val dao = TestDao(emptyList())

        var isMapperCalled = false
        val mapper = object : CacheBook.Mapper<DomainBooksDetail> {
            override fun map(
                id: String, title: String, description: String,
                author: String, releaseDate: String, imageUrl: String
            ): DomainBooksDetail {
                isMapperCalled = true
                return DomainBooksDetail(title, description, author, releaseDate, imageUrl)
            }
        }

        val repository = DefaultBookDetailsRepository(dao, mapper)

        runBlocking {
            val actualDomainBook = repository.findBookWithId("1")

            assertFalse(isMapperCalled)
            assertEquals(DetailResult.NoBookFound, actualDomainBook)
        }
    }

    private class TestDao(private val books: List<CacheBook>) : BooksDao {

        override fun getBookById(id: String): CacheBook? {
            return books.find { it.id == id }
        }

        override fun getAllBooks(): List<CacheBook> = books

        override fun insertBooks(books: List<CacheBook>) = Unit
    }
}