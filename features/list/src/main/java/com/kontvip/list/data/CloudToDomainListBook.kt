package com.kontvip.list.data

import com.kontvip.common.data.cloud.model.CloudBook
import com.kontvip.list.domain.model.DomainListBook

class CloudToDomainListBook : CloudBook.Mapper<DomainListBook> {
    override fun map(
        id: String, title: String, description: String,
        author: String, releaseDate: String, imageUrl: String
    ): DomainListBook {
        return DomainListBook(id, title, description, imageUrl)
    }
}