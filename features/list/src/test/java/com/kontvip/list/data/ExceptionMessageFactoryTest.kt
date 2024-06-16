package com.kontvip.list.data

import androidx.annotation.StringRes
import com.kontvip.common.core.StringProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class ExceptionMessageFactoryTest {

    private lateinit var stringProvider: FakeStringProvider
    private lateinit var exceptionMessageFactory: ExceptionMessageFactory.Default

    @Before
    fun setup() {
        stringProvider = FakeStringProvider()
        exceptionMessageFactory = ExceptionMessageFactory.Default(stringProvider)
    }

    @Test
    fun `map should return correct message for InternalError`() {
        val exception = InternalError()
        val expectedResourceId = com.kontvip.common.R.string.internal_error_message
        stringProvider.addStringResource(expectedResourceId, "Internal Server Error")

        val result = exceptionMessageFactory.map(exception)

        assertEquals("Internal Server Error", result)
    }

    @Test
    fun `map should return correct message for UnknownHostException`() {
        val exception = UnknownHostException()
        val expectedResourceId = com.kontvip.common.R.string.no_internet_connect_error_message
        stringProvider.addStringResource(expectedResourceId, "No Internet Connection")

        val result = exceptionMessageFactory.map(exception)

        assertEquals("No Internet Connection", result)
    }

    @Test
    fun `map should return default message for other exceptions`() {
        val exception = RuntimeException()
        val expectedResourceId = com.kontvip.common.R.string.service_unavailable_error_message
        stringProvider.addStringResource(expectedResourceId, "Service Unavailable")

        val result = exceptionMessageFactory.map(exception)

        assertEquals("Service Unavailable", result)
    }

    private class FakeStringProvider : StringProvider {

        private val stringMap: MutableMap<Int, String> = mutableMapOf()

        fun addStringResource(@StringRes stringRes: Int, value: String) {
            stringMap[stringRes] = value
        }

        override fun string(stringRes: Int): String {
            return stringMap[stringRes]!!
        }
    }

}