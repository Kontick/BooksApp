package com.kontvip.list.di

import com.kontvip.common.core.DispatcherList
import com.kontvip.common.core.StringProvider
import com.kontvip.common.data.cache.BooksDao
import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.common.data.cloud.BooksApi
import com.kontvip.common.data.cloud.model.CloudBook
import com.kontvip.common.navigation.ListRouteProvider
import com.kontvip.common.navigation.RouteBuilder
import com.kontvip.list.core.DefaultListRouteProvider
import com.kontvip.list.core.ListRouteBuilder
import com.kontvip.list.data.date.BooksAppDateParser
import com.kontvip.list.data.CacheToDomainListBookMapper
import com.kontvip.list.data.CloudToCacheBookMapper
import com.kontvip.list.data.DefaultListRepository
import com.kontvip.list.data.ExceptionMessageFactory
import com.kontvip.list.domain.FetchBooksUseCase
import com.kontvip.list.domain.core.BooksListUiFactory
import com.kontvip.list.domain.core.ListRepository
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.domain.model.ListResult
import com.kontvip.list.presentation.core.DefaultBooksListUiFactory
import com.kontvip.list.presentation.core.DomainToBookUiMapper
import com.kontvip.list.presentation.core.FailResultToListScreenUiState
import com.kontvip.list.presentation.core.SuccessResultToListScreenUiState
import com.kontvip.list.presentation.model.BookUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ListModule {

    @Provides
    @Singleton
    fun provideListRouteProvider(): ListRouteProvider = DefaultListRouteProvider()

    @Provides
    @IntoSet
    fun provideListRouteBuilder(): RouteBuilder = ListRouteBuilder()

    @Provides
    @Singleton
    fun provideFetchBookUseCase(
        repository: ListRepository,
        booksListUiFactory: BooksListUiFactory,
        dispatcherList: DispatcherList
    ): FetchBooksUseCase = FetchBooksUseCase.Default(
        repository = repository,
        booksListUiFactory = booksListUiFactory,
        dispatcherList = dispatcherList
    )

    @Provides
    @Singleton
    fun provideListRepository(
        dao: BooksDao,
        api: BooksApi,
        cacheToDomainBookMapper: CacheBook.Mapper<DomainListBook>,
        cloudToCacheBookMapper: CloudBook.Mapper<CacheBook>,
        exceptionMessageFactory: ExceptionMessageFactory
    ): ListRepository = DefaultListRepository(
        dao = dao,
        api = api,
        cacheToDomainBookMapper = cacheToDomainBookMapper,
        cloudToCacheBookMapper = cloudToCacheBookMapper,
        exceptionMessageFactory = exceptionMessageFactory
    )

    @Provides
    @Singleton
    fun provideCacheToDomainListBook(): CacheBook.Mapper<DomainListBook> = CacheToDomainListBookMapper()

    @Provides
    @Singleton
    fun provideCloudToCacheBookMapper(
        dateParsers: Set<@JvmSuppressWildcards BooksAppDateParser>,
        stringProvider: StringProvider
    ): CloudBook.Mapper<CacheBook> = CloudToCacheBookMapper(
        dateParsers = dateParsers,
        stringProvider = stringProvider
    )

    @Provides
    @Singleton
    fun provideBooksListUiFactory(
        successResultToListScreenUiState: ListResult.Success.Mapper<ListScreenUiState>,
        failResultToListScreenUiState: ListResult.Fail.Mapper<ListScreenUiState>
    ): BooksListUiFactory = DefaultBooksListUiFactory(
        successResultToListScreenUiState = successResultToListScreenUiState,
        failResultToListScreenUiState = failResultToListScreenUiState
    )

    @Provides
    @Singleton
    fun provideExceptionMessageFactory(
        stringProvider: StringProvider
    ): ExceptionMessageFactory = ExceptionMessageFactory.Default(
        stringProvider = stringProvider
    )

    @Provides
    @Singleton
    fun provideSuccessResultToListScreenUiState(
        domainToBookUiMapper: DomainListBook.Mapper<BookUi>
    ): ListResult.Success.Mapper<ListScreenUiState> = SuccessResultToListScreenUiState(
        domainToBookUiMapper = domainToBookUiMapper
    )

    @Provides
    @Singleton
    fun provideDomainToBookUiMapper(): DomainListBook.Mapper<BookUi> = DomainToBookUiMapper()

    @Provides
    @Singleton
    fun provideFailResultToListScreenUiState(): ListResult.Fail.Mapper<ListScreenUiState> =
        FailResultToListScreenUiState()

    @Provides
    @IntoSet
    fun providesRegularDateParser(): BooksAppDateParser = BooksAppDateParser.RegularDateParser

    @Provides
    @IntoSet
    fun providesYearOnlyDateParser(): BooksAppDateParser = BooksAppDateParser.YearOnlyDateParser

    @Provides
    @IntoSet
    fun providesYearsBCEDateParser(): BooksAppDateParser = BooksAppDateParser.YearsBCEDateParser
}