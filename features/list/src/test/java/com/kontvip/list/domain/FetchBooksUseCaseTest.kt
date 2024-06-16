package com.kontvip.list.domain

import com.kontvip.common.core.DispatcherList
import com.kontvip.list.domain.core.BooksListUiFactory
import com.kontvip.list.domain.core.ListRepository
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.domain.model.ListResult
import com.kontvip.list.presentation.model.BookUi
import com.kontvip.list.presentation.model.FailUiState
import com.kontvip.list.presentation.model.ListUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FetchBooksUseCaseTest {

    @Test
    fun `invoke should retry fetching books from cloud 3 times with 2 seconds interval if ListResult shouldRequestAgain returns true`() = runBlocking {
        val fakeRepository = FakeListRepository()
        val fakeFactory = FakeBooksListUiFactory()
        //must be real in order to test actual passed time
        val realDispatchers = DispatcherList.Default()

        val fetchBooksUseCase = FetchBooksUseCase.Default(fakeRepository, fakeFactory, realDispatchers)

        fakeRepository.getCachedBooksResult = ListResult.Success(
            listOf(DomainListBook("1", "t", "d", "i"))
        )
        fakeRepository.fetchBooksFromCloudResult = ListResult.Fail("fail", true)

        val capturedUiStates = mutableListOf<ListScreenUiState>()

        val timeBeforeInvoke = System.currentTimeMillis()
        fetchBooksUseCase.invoke { uiState ->
            capturedUiStates.add(uiState)
        }
        val timeAfterInvoke = System.currentTimeMillis()

        //3 delays of 2 seconds + 100 mills buffer
        assertTrue(timeAfterInvoke - timeBeforeInvoke in 6000..6100)
        assertEquals(1, capturedUiStates.size)

        val expectedCachedFirstListScreenUiState = fakeFactory.construct(ListResult.Success(
            listOf(DomainListBook("1", "t", "d", "i"))
        ))
        assertEquals(expectedCachedFirstListScreenUiState, capturedUiStates[0])
    }

    @Test
    fun `invoke should fetch books from cloud and invoke onResultBlock if has cache with cached books and then with cloud books`() = runTest {
        val fakeRepository = FakeListRepository()
        val fakeFactory = FakeBooksListUiFactory()
        val fakeDispatcherList = FakeDispatcherList()

        val fetchBooksUseCase = FetchBooksUseCase.Default(fakeRepository, fakeFactory, fakeDispatcherList)

        fakeRepository.getCachedBooksResult = ListResult.Success(
            listOf(DomainListBook("1", "t", "d", "i"))
        )
        fakeRepository.fetchBooksFromCloudResult = ListResult.Success(
            listOf(DomainListBook("2", "t2", "d2", "i2"))
        )

        val capturedUiStates = mutableListOf<ListScreenUiState>()
        fetchBooksUseCase.invoke { uiState ->
            capturedUiStates.add(uiState)
        }

        assertEquals(2, capturedUiStates.size)

        val expectedCachedFirstListScreenUiState = fakeFactory.construct(ListResult.Success(
            listOf(DomainListBook("1", "t", "d", "i"))
        ))
        assertEquals(expectedCachedFirstListScreenUiState, capturedUiStates[0])

        val expectedCloudFirstListScreenUiState = fakeFactory.construct(ListResult.Success(
            listOf(DomainListBook("2", "t2", "d2", "i2"))
        ))
        assertEquals(expectedCloudFirstListScreenUiState, capturedUiStates[1])
    }

    @Test
    fun `invoke should fetch books from cloud and invoke onResultBlock ONLY with cloud books if has NO cache`() = runTest {
        val fakeRepository = FakeListRepository()
        val fakeFactory = FakeBooksListUiFactory()
        val fakeDispatcherList = FakeDispatcherList()

        val fetchBooksUseCase = FetchBooksUseCase.Default(fakeRepository, fakeFactory, fakeDispatcherList)

        fakeRepository.getCachedBooksResult = ListResult.Success(
            listOf(DomainListBook("1", "t", "d", "i"))
        )
        fakeRepository.fetchBooksFromCloudResult = ListResult.Success(
            listOf(DomainListBook("2", "t2", "d2", "i2"))
        )

        val capturedUiStates = mutableListOf<ListScreenUiState>()
        fetchBooksUseCase.invoke { uiState ->
            capturedUiStates.add(uiState)
        }

        assertEquals(2, capturedUiStates.size)

        val expectedCachedFirstListScreenUiState = fakeFactory.construct(ListResult.Success(
            listOf(DomainListBook("1", "t", "d", "i"))
        ))
        assertEquals(expectedCachedFirstListScreenUiState, capturedUiStates[0])

        val expectedCloudFirstListScreenUiState = fakeFactory.construct(ListResult.Success(
            listOf(DomainListBook("2", "t2", "d2", "i2"))
        ))
        assertEquals(expectedCloudFirstListScreenUiState, capturedUiStates[1])
    }

    @Test
    fun `invoke method should invoke onResultBlock with FailListUiState if result isFail return true and cache canBeDisplayed returns true`() = runTest {
        val fakeRepository = FakeListRepository()
        val fakeFactory = FakeBooksListUiFactory()
        val fakeDispatcherList = FakeDispatcherList()

        val fetchBooksUseCase = FetchBooksUseCase.Default(fakeRepository, fakeFactory, fakeDispatcherList)

        fakeRepository.getCachedBooksResult = ListResult.Success(emptyList())
        fakeRepository.fetchBooksFromCloudResult = ListResult.Fail("fail", false)

        val capturedUiStates = mutableListOf<ListScreenUiState>()
        fetchBooksUseCase.invoke { uiState ->
            capturedUiStates.add(uiState)
        }

        assertEquals(1, capturedUiStates.size)

        val expectedScreenUiState = fakeFactory.construct(ListResult.Fail(
            "fail", false
        ))
        assertEquals(expectedScreenUiState, capturedUiStates[0])
    }

    private class FakeDispatcherList : DispatcherList {
        override fun ui(): CoroutineDispatcher = UnconfinedTestDispatcher()
        override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()
    }

    private class FakeListRepository : ListRepository {
        var getCachedBooksResult: ListResult = ListResult.Success(emptyList())
        var fetchBooksFromCloudResult: ListResult = ListResult.Success(emptyList())

        override suspend fun fetchBooksFromCloud(): ListResult = fetchBooksFromCloudResult
        override fun getCachedBooks(): ListResult = getCachedBooksResult
    }

    private class FakeBooksListUiFactory : BooksListUiFactory {
        override fun construct(listResult: ListResult): ListScreenUiState {
            return listResult.map(
                successMapper = object : ListResult.Success.Mapper<ListScreenUiState> {
                    private val domainToUiListMapper = FakeDomainToBookUiMapper()

                    override fun map(books: List<DomainListBook>): ListScreenUiState {
                        return ListUiState(books.map { it.map(domainToUiListMapper) })
                    }
                },
                failMapper = object : ListResult.Fail.Mapper<ListScreenUiState> {
                    override fun map(errorMessage: String): ListScreenUiState {
                        return FailUiState(errorMessage)
                    }
                }
            )
        }
    }

    private class FakeDomainToBookUiMapper : DomainListBook.Mapper<BookUi> {
        override fun map(
            id: String, title: String, description: String, imageUrl: String
        ): BookUi {
            return BookUi(id, title, description, imageUrl)
        }
    }
}