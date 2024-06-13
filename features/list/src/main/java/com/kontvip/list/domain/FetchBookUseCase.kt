package com.kontvip.list.domain

import com.kontvip.list.domain.core.BooksListUiFactory
import com.kontvip.list.domain.core.ListRepository
import com.kontvip.list.domain.core.ListScreenUiState

interface FetchBookUseCase {

    suspend fun invoke(onResultBlock: (ListScreenUiState) -> Unit)

    class Default(
        private val repository: ListRepository,
        private val booksListUiFactory: BooksListUiFactory
    ) : FetchBookUseCase {

        companion object {
            private const val MAX_REPEAT_COUNT = 3
        }

        override suspend fun invoke(onResultBlock: (ListScreenUiState) -> Unit) {
            val cache = booksListUiFactory.construct(repository.getCachedBooks())
            if (cache.canBeDisplayed()) {
                onResultBlock.invoke(cache)
            }
            var result = repository.fetchBooksFromCloud()
            var repeatCount = 0
            while (repeatCount < MAX_REPEAT_COUNT && result.shouldRequestAgain()) {
                result = repository.fetchBooksFromCloud()
                repeatCount++
            }
            onResultBlock.invoke(booksListUiFactory.construct(result))
        }
    }

}

