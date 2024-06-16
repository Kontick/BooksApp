package com.kontvip.list.domain.model

import org.junit.Assert.*
import org.junit.Test

class ListResultTest {

    @Test
    fun `Success result should return true when isSuccessful called and map using success mapper`() {
        val books = listOf(DomainListBook("1", "title", "description", "https://test.com"))
        val successResult = ListResult.Success(books)

        val isSuccessful = successResult.isSuccessful()
        val shouldRequestAgain = successResult.shouldRequestAgain()
        val mappedResult = successResult.map(MockSuccessMapper(), MockFailMapper())

        assertTrue(isSuccessful)
        assertFalse(shouldRequestAgain)
        assertTrue(mappedResult == "Success")
    }

    @Test
    fun `Fail result should return false when isSuccessful called and map using fail mapper`() {
        val errorMessage = "Failed to fetch books"
        val shouldRequestAgain = true
        val failResult = ListResult.Fail(errorMessage, shouldRequestAgain)

        val isSuccessful = failResult.isSuccessful()
        val shouldRequestAgainResult = failResult.shouldRequestAgain()
        val mappedResult = failResult.map(MockSuccessMapper(), MockFailMapper())

        assertFalse(isSuccessful)
        assertTrue(shouldRequestAgainResult)
        assertEquals("Fail", mappedResult)
    }

    private class MockSuccessMapper : ListResult.Success.Mapper<String> {
        override fun map(books: List<DomainListBook>): String {
            return "Success"
        }
    }

    private class MockFailMapper : ListResult.Fail.Mapper<String> {
        override fun map(errorMessage: String): String {
            return "Fail"
        }
    }
}