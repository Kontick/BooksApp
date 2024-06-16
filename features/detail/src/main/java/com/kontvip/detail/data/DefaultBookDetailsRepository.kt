package com.kontvip.detail.data

import com.kontvip.common.data.cache.BooksDao
import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.detail.domain.BookDetailsRepository
import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DomainBooksDetail

class DefaultBookDetailsRepository(
    private val booksDao: BooksDao,
    private val cacheToDomainBooksDetailMapper: CacheBook.Mapper<DomainBooksDetail>
) : BookDetailsRepository {
    override suspend fun findBookWithId(bookId: String): DetailResult {
        return booksDao.getBookById(bookId)?.map(cacheToDomainBooksDetailMapper)
            ?.let { DetailResult.Success(it) } ?: DetailResult.NoBookFound
    }
}