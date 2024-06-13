package com.kontvip.list.presentation.core

import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.domain.core.BooksListUiFactory
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.domain.model.ListResult
import com.kontvip.list.presentation.model.BookUi
import com.kontvip.list.presentation.model.FailUiState
import com.kontvip.list.presentation.model.ListUiState

class DefaultBooksListUiFactory(

) : BooksListUiFactory {
    override fun construct(listResult: ListResult): ListScreenUiState {

        val domainToUiBookMapper = object : DomainListBook.Mapper<BookUi> {
            override fun map(
                id: String, title: String, description: String, imageUrl: String
            ): BookUi {
                return BookUi(id, title, description, imageUrl)
            }
        }

        return listResult.map(
            object : ListResult.Success.Mapper<ListScreenUiState> {
                override fun map(books: List<DomainListBook>): ListScreenUiState {
                    return ListUiState(books.map { it.map(domainToUiBookMapper) })
                }
            },
            object : ListResult.Fail.Mapper<ListScreenUiState> {
                override fun map(errorMessage: String): ListScreenUiState {
                    return FailUiState(errorMessage)
                }
            }
        )
    }
}