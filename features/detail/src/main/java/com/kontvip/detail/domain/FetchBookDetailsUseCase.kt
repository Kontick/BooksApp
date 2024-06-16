package com.kontvip.detail.domain

import com.kontvip.common.core.DispatcherList
import com.kontvip.detail.domain.model.DetailScreenUiState
import kotlinx.coroutines.withContext

interface FetchBookDetailsUseCase {

    suspend fun invoke(bookId: String): DetailScreenUiState

    class Default(
        private val bookDetailsRepository: BookDetailsRepository,
        private val booksDetailUiFactory: BooksDetailUiFactory,
        private val dispatcherList: DispatcherList
    ) : FetchBookDetailsUseCase {

        override suspend fun invoke(bookId: String): DetailScreenUiState {
            return withContext(dispatcherList.io()) {
                val result = bookDetailsRepository.findBookWithId(bookId = bookId)
                booksDetailUiFactory.construct(result)
            }
        }
    }
}