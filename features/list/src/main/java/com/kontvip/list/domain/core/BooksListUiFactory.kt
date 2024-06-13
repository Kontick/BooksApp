package com.kontvip.list.domain.core

import com.kontvip.list.domain.model.ListResult

interface BooksListUiFactory {
    fun construct(listResult: ListResult): ListScreenUiState
}