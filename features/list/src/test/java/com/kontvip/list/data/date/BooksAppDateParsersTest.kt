package com.kontvip.list.data.date

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class BooksAppDateParsersTest {
    @Test
    fun `RegularDateParser should parse 'dd mm yyyy' date format`() {
        val dateString = "16/06/2024"
        val actual = BooksAppDateParser.RegularDateParser.parse(dateString)

        assertEquals("Sun, Jun 16, '24", actual?.first)
        assertEquals(getMillisForDate("2024-06-16"), actual?.second)

        val dateString2 = "16/06/1924"
        val actual2 = BooksAppDateParser.RegularDateParser.parse(dateString2)

        assertEquals("Mon, Jun 16, 1924", actual2?.first)
        assertEquals(getMillisForDate("1924-06-16"), actual2?.second)
    }

    @Test
    fun `RegularDateParser should return null for invalid date`() {
        val dateString = "2024-06-16"
        val actual = BooksAppDateParser.RegularDateParser.parse(dateString)
        assertNull(actual)
    }

    @Test
    fun `YearOnlyDateParser should parse 'yyyy' date format`() {
        val dateString = "2024"
        val actual = BooksAppDateParser.YearOnlyDateParser.parse(dateString)

        assertEquals("2024", actual?.first)
        assertEquals(getMillisForDate("2024-01-01"), actual?.second)
    }

    @Test
    fun `YearOnlyDateParser should return null for invalid date`() {
        val dateString = "2024-06-16"
        val actual = BooksAppDateParser.YearOnlyDateParser.parse(dateString)
        assertNull(actual)
    }

    @Test
    fun `YearsBCEDateParser should parse 'yyyy G' date format`() {
        val dateString = "500 BC"
        val actual = BooksAppDateParser.YearsBCEDateParser.parse(dateString)

        assertEquals("500 BC", actual?.first)
    }

    @Test
    fun `YearsBCEDateParser should return null for invalid date`() {
        val dateString = "500 AD"
        val actual = BooksAppDateParser.YearsBCEDateParser.parse(dateString)
        assertNull(actual)
    }

    private fun getMillisForDate(dateString: String): Long {
        val calendar = Calendar.getInstance().apply {
            time = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(dateString) ?: Date()
        }
        return calendar.timeInMillis
    }
}