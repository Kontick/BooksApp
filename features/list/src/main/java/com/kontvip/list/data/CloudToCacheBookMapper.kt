package com.kontvip.list.data

import com.kontvip.common.core.StringProvider
import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.common.data.cloud.model.CloudBook
import com.kontvip.list.data.date.BooksAppDateParser

class CloudToCacheBookMapper(
    private val dateParsers: Set<BooksAppDateParser>,
    private val stringProvider: StringProvider
) : CloudBook.Mapper<CacheBook> {

    override fun map(
        id: String, title: String, description: String,
        author: String, releaseDate: String, imageUrl: String
    ): CacheBook {
        val formattedDateAndMills = dateParsers.firstNotNullOfOrNull { parser ->
            parser.parse(releaseDate)
        } ?: FormattedDateAndMills(releaseDate, 0L)

        return CacheBook(
            id,
            title.ifBlank { stringProvider.string(com.kontvip.common.R.string.no_title) },
            description,
            author,
            formattedDateAndMills.first,
            formattedDateAndMills.second,
            imageUrl
        )
    }
}