package com.kontvip.list.presentation.core

import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.domain.model.ListResult
import com.kontvip.list.presentation.model.BookUi
import com.kontvip.list.presentation.model.ListUiState

class SuccessResultToListScreenUiState(
    private val domainToBookUiMapper: DomainListBook.Mapper<BookUi>,
    ) : ListResult.Success.Mapper<ListScreenUiState> {
    override fun map(books: List<DomainListBook>): ListScreenUiState {
        return ListUiState(books.map { it.map(domainToBookUiMapper) })
    }
}