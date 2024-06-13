package com.kontvip.list.data

import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.common.data.cloud.model.CloudBook

class CloudToCacheBookMapper : CloudBook.Mapper<CacheBook> {
    override fun map(
        id: String, title: String, description: String,
        author: String, releaseDate: String, imageUrl: String
    ): CacheBook {
        return CacheBook(id, title, description, author, releaseDate, imageUrl)
    }
}