package com.kontvip.detail.domain.model

import org.junit.Assert.*
import org.junit.Test

class DetailResultTest {

    @Test
    fun `should map Success result to expected value`() {
        val domainDetail = DomainBooksDetail("123", "Title", "Author", "r", "h")
        val successResult = DetailResult.Success(domainDetail)

        val successMapper = FakeSuccessMapper()
        val failMapper = FakeFailMapper()

        val mappedResult = successResult.map(successMapper, failMapper)

        assertEquals("Success", mappedResult)
    }

    @Test
    fun `should map NoBookFound result to expected value`() {
        val noBookFoundResult = DetailResult.NoBookFound

        val successMapper = FakeSuccessMapper()
        val failMapper = FakeFailMapper()

        val mappedResult = noBookFoundResult.map(successMapper, failMapper)

        assertEquals("Fail", mappedResult)
    }

    private class FakeSuccessMapper : DetailResult.Success.Mapper<String> {
        override fun map(domainBooksDetail: DomainBooksDetail): String {
            return "Success"
        }
    }

    private class FakeFailMapper : DetailResult.NoBookFound.Mapper<String> {
        override fun map(): String {
            return "Fail"
        }
    }
}