package com.kontvip.list.presentation.core

import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.presentation.model.BookUi

class DomainToBookUiMapper : DomainListBook.Mapper<BookUi> {
    override fun map(id: String, title: String, description: String, imageUrl: String): BookUi {
        return BookUi(id, title, description, imageUrl)
    }
}