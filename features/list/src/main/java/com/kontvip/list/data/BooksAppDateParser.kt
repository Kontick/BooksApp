package com.kontvip.list.data

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

typealias FormattedDateAndMills = Pair<String, Long>

abstract class BooksAppDateParser(datePattern: String, regexPattern: String) {

    private val dateFormat = SimpleDateFormat(datePattern, Locale.US)
    private val regex = Regex(regexPattern)

    fun parse(originalDate: String): FormattedDateAndMills? {
        return if (originalDate.matches(regex)) {
            val date = dateFormat.parse(originalDate) ?: return null
            val dateInMillis = date.time
            val formattedDate = formatDate(date).ifBlank { originalDate }
            Pair(formattedDate, dateInMillis)
        } else {
            null
        }
    }

    protected open fun formatDate(date: Date): String = ""

    object RegularDateParser : BooksAppDateParser(
        datePattern = "dd/MM/yyyy",
        regexPattern = "\\d{1,2}/\\d{1,2}/\\d{4}"
    ) {
        private const val AFTER_2000_DATE_FORMAT = "EEE, MMM d, ''yy"
        private const val BEFORE_2000_DATE_FORMAT = "EEE, MMM d, yyyy"

        override fun formatDate(date: Date): String {
            val calendar = Calendar.getInstance().apply { time = date }
            val year = calendar.get(Calendar.YEAR)
            return SimpleDateFormat(
                if (year >= 2000) AFTER_2000_DATE_FORMAT else BEFORE_2000_DATE_FORMAT, Locale.US
            ).format(date)
        }
    }

    object YearOnlyDateParser : BooksAppDateParser(
        datePattern = "yyyy",
        regexPattern = "\\d{4}"
    )

    object YearsBCEDateParser : BooksAppDateParser(
        datePattern = "yyyy G",
        regexPattern = "\\d{1,4} BC"
    )
}