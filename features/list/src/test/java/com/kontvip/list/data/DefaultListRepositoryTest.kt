package com.kontvip.list.data

import com.kontvip.common.data.cache.BooksDao
import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.common.data.cloud.BooksApi
import com.kontvip.common.data.cloud.model.CloudBook
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.domain.model.ListResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

class DefaultListRepositoryTest {

    private lateinit var repository: DefaultListRepository
    private lateinit var fakeDao: FakeDao
    private lateinit var fakeApi: FakeApi
    private lateinit var fakeCacheToDomainBookMapper: FakeCacheToDomainBookMapper
    private lateinit var fakeCloudToCacheBookMapper: FakeCloudToCacheBookMapper
    private lateinit var fakeExceptionMessageFactory: FakeExceptionMessageFactory

    @Before
    fun setup() {
        fakeDao  = FakeDao()
        fakeApi = FakeApi()
        fakeCacheToDomainBookMapper = FakeCacheToDomainBookMapper()
        fakeCloudToCacheBookMapper = FakeCloudToCacheBookMapper()
        fakeExceptionMessageFactory = FakeExceptionMessageFactory()
        repository = DefaultListRepository(
            fakeDao, fakeApi, fakeCacheToDomainBookMapper,
            fakeCloudToCacheBookMapper, fakeExceptionMessageFactory,
        )
    }

    @Test
    fun `fetchBooksFromCloud should return Success result on successful API response`() = runTest {
        val cloudBook = CloudBook("1", "t", "d", "a", "r", "i")
        fakeApi.fetchBookResponse = Response.success(listOf(cloudBook))

        assertEquals(fakeDao.getAllBooks(), emptyList<CacheBook>())

        val actualListResult = repository.fetchBooksFromCloud()
        val expectedCacheBook = CacheBook("1", "t", "d", "a", "r", 0L, "i")

        assertEquals(1, fakeCacheToDomainBookMapper.mapCalledCount)
        assertEquals(1, fakeCloudToCacheBookMapper.mapCalledCount)
        assertEquals(listOf(expectedCacheBook), fakeDao.getAllBooks())

        val expectedDomainListBook = DomainListBook("1", "t", "d", "i")
        val expectedListResult = ListResult.Success(listOf(expectedDomainListBook))

        assertEquals(expectedListResult, actualListResult)
    }

    @Test
    fun `fetchBooksFromCloud should return Fail result on 500 Internal Server Error`() = runTest {
        fakeApi.fetchBookResponse = Response.error(500, "".toResponseBody())

        assertEquals(fakeDao.getAllBooks(), emptyList<CacheBook>())

        val actualListResult = repository.fetchBooksFromCloud()

        assertEquals(emptyList<CacheBook>(), fakeDao.getAllBooks())
        assertEquals(InternalError().toString(), fakeExceptionMessageFactory.exceptionPassed.toString())
        assertEquals(0, fakeCacheToDomainBookMapper.mapCalledCount)
        assertEquals(0, fakeCloudToCacheBookMapper.mapCalledCount)

        val expectedListResult = ListResult.Fail("fail", true)

        assertEquals(expectedListResult, actualListResult)
    }

    @Test
    fun `fetchBooksFromCloud should return Fail result on UnknownHostException`() = runTest {
        fakeApi.exceptionToThrow = UnknownHostException()

        assertEquals(fakeDao.getAllBooks(), emptyList<CacheBook>())

        val actualListResult = repository.fetchBooksFromCloud()

        assertEquals(emptyList<CacheBook>(), fakeDao.getAllBooks())
        assertEquals(UnknownHostException().toString(), fakeExceptionMessageFactory.exceptionPassed.toString())
        assertEquals(0, fakeCacheToDomainBookMapper.mapCalledCount)
        assertEquals(0, fakeCloudToCacheBookMapper.mapCalledCount)

        val expectedListResult = ListResult.Fail("fail", true)

        assertEquals(expectedListResult, actualListResult)
    }

    @Test
    fun `getCachedBooks should return Success result with cached books`() = runTest {
        val cacheBook = CacheBook("1", "t", "d", "a", "r", 0L, "i")
        fakeDao.cachedBooks = listOf(cacheBook.copy(), cacheBook.copy())

        val actualListResult = repository.getCachedBooks()
        val domainListBook = DomainListBook("1", "t", "d", "i")
        val expectedListResult = ListResult.Success(listOf(domainListBook.copy(), domainListBook.copy()))

        assertEquals(2, fakeCacheToDomainBookMapper.mapCalledCount)
        assertEquals(expectedListResult, actualListResult)
    }

    private class FakeDao : BooksDao {
        var cachedBooks: List<CacheBook> = listOf()

        override fun getBookById(id: String): CacheBook? {
            return cachedBooks.find { it.id == id }
        }

        override fun getAllBooks(): List<CacheBook> {
            return cachedBooks
        }

        override fun insertBooks(books: List<CacheBook>) {
            cachedBooks = books
        }
    }

    private class FakeApi : BooksApi {
        lateinit var fetchBookResponse: Response<List<CloudBook>>
        var exceptionToThrow : Throwable? = null

        override suspend fun fetchBooks(): Response<List<CloudBook>> {
            exceptionToThrow?.let { throw it }
            return fetchBookResponse
        }
    }

    private class FakeCacheToDomainBookMapper : CacheBook.Mapper<DomainListBook> {
        var mapCalledCount = 0
        override fun map(
            id: String, title: String, description: String,
            author: String, releaseDate: String, imageUrl: String
        ): DomainListBook {
            mapCalledCount++
            return DomainListBook(id, title, description, imageUrl)
        }

    }

    private class FakeCloudToCacheBookMapper : CloudBook.Mapper<CacheBook> {
        var mapCalledCount = 0
        override fun map(
            id: String, title: String, description: String,
            author: String, releaseDate: String, imageUrl: String
        ): CacheBook {
            mapCalledCount++
            return CacheBook(id, title, description, author, releaseDate, 0L, imageUrl)
        }

    }

    private class FakeExceptionMessageFactory : ExceptionMessageFactory {
        var exceptionPassed: Throwable? = null
        override fun map(exception: Throwable): String {
            exceptionPassed = exception
            return "fail"
        }
    }
}