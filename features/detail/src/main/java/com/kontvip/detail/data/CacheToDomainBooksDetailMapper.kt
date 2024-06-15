package com.kontvip.detail.data

import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.detail.domain.model.DomainBooksDetail

class CacheToDomainBooksDetailMapper : CacheBook.Mapper<DomainBooksDetail> {
    override fun map(
        id: String, title: String, description: String,
        author: String, releaseDate: String, imageUrl: String
    ): DomainBooksDetail {
        return DomainBooksDetail(title, description, author, releaseDate, imageUrl)
    }
}