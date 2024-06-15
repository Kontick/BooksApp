package com.kontvip.list.presentation.core

import com.kontvip.list.domain.core.BooksListUiFactory
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.domain.model.ListResult

class DefaultBooksListUiFactory(
    private val successResultToListScreenUiState: ListResult.Success.Mapper<ListScreenUiState>,
    private val failResultToListScreenUiState: ListResult.Fail.Mapper<ListScreenUiState>
) : BooksListUiFactory {
    override fun construct(listResult: ListResult): ListScreenUiState {
        return listResult.map(successResultToListScreenUiState, failResultToListScreenUiState)
    }
}