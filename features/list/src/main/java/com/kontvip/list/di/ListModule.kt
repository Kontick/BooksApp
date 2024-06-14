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
import com.kontvip.list.data.CacheToDomainListBook
import com.kontvip.list.data.CloudToCacheBookMapper
import com.kontvip.list.data.CloudToDomainListBook
import com.kontvip.list.data.DefaultListRepository
import com.kontvip.list.data.ExceptionMessageFactory
import com.kontvip.list.domain.FetchBookUseCase
import com.kontvip.list.domain.core.BooksListUiFactory
import com.kontvip.list.domain.core.ListRepository
import com.kontvip.list.domain.model.DomainListBook
import com.kontvip.list.presentation.core.DefaultBooksListUiFactory
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
    ): FetchBookUseCase = FetchBookUseCase.Default(
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
        cloudToDomainBookMapper: CloudBook.Mapper<DomainListBook>,
        cloudToCacheBookMapper: CloudBook.Mapper<CacheBook>,
        exceptionMessageFactory: ExceptionMessageFactory
    ): ListRepository = DefaultListRepository(
        dao = dao,
        api = api,
        cacheToDomainBookMapper = cacheToDomainBookMapper,
        cloudToDomainBookMapper = cloudToDomainBookMapper,
        cloudToCacheBookMapper = cloudToCacheBookMapper,
        exceptionMessageFactory = exceptionMessageFactory
    )

    @Provides
    @Singleton
    fun provideCacheToDomainListBook(): CacheBook.Mapper<DomainListBook> = CacheToDomainListBook()

    @Provides
    @Singleton
    fun provideCloudToDomainListBook(): CloudBook.Mapper<DomainListBook> = CloudToDomainListBook()

    @Provides
    @Singleton
    fun provideCloudToCacheBookMapper(): CloudBook.Mapper<CacheBook> = CloudToCacheBookMapper()

    @Provides
    @Singleton
    fun provideBooksListUiFactory(): BooksListUiFactory = DefaultBooksListUiFactory()

    @Provides
    @Singleton
    fun provideExceptionMessageFactory(
        stringProvider: StringProvider
    ): ExceptionMessageFactory = ExceptionMessageFactory.Default(
        stringProvider = stringProvider
    )
}