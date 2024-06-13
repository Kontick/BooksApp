package com.kontvip.list.data

import com.kontvip.common.data.cache.BooksDao
import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.common.data.cloud.BooksApi
import com.kontvip.common.data.cloud.model.CloudBook
import com.kontvip.list.domain.core.ListRepository
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.domain.model.ListResult
import java.lang.Exception
import java.net.UnknownHostException

class DefaultListRepository(
    private val dao: BooksDao,
    private val api: BooksApi,
    private val cacheToDomainBookMapper: CacheBook.Mapper<DomainListBook>,
    private val cloudToDomainBookMapper: CloudBook.Mapper<DomainListBook>,
    private val cloudToCacheBookMapper : CloudBook.Mapper<CacheBook>,
    private val exceptionMessageFactory: ExceptionMessageFactory
) : ListRepository {

    companion object {
        private const val MAX_REPEAT_REQUEST_COUNT = 3
    }

    override suspend fun fetchBooksFromCloud(requestRepeatCount: Int): ListResult {
        try {
            val response = api.fetchBooks()

            val code = response.code()
            if (code == 500) {
                return ListResult.Fail(
                    exceptionMessageFactory.map(
                        InternalError()
                    ),
                    requestRepeatCount <= MAX_REPEAT_REQUEST_COUNT
                )
            }
            val cloudBooks = response.body()!!
            dao.insertBooks(cloudBooks.map { it.map(cloudToCacheBookMapper) })
            return ListResult.Success(cloudBooks.map { it.map(cloudToDomainBookMapper) })
        } catch (e: UnknownHostException) {
            return ListResult.Fail(
                exceptionMessageFactory.map(e),
                requestRepeatCount <= MAX_REPEAT_REQUEST_COUNT
            )
        } catch (e: Exception) {
            return ListResult.Fail(exceptionMessageFactory.map(e), false)
        }
    }

    override fun getCachedBooks(): ListResult {
        val cacheBooks = dao.getAllBooks()
        return ListResult.Success(cacheBooks.map { it.map(cacheToDomainBookMapper) })
    }

}