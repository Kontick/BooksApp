package com.kontvip.detail.di

import com.kontvip.common.core.DispatcherList
import com.kontvip.common.data.cache.BooksDao
import com.kontvip.common.data.cache.model.CacheBook
import com.kontvip.common.navigation.DetailRouteProvider
import com.kontvip.common.navigation.RouteBuilder
import com.kontvip.detail.core.DefaultDetailRouteProvider
import com.kontvip.detail.core.DetailRouteBuilder
import com.kontvip.detail.data.CacheToDomainBooksDetailMapper
import com.kontvip.detail.data.DefaultBookDetailsRepository
import com.kontvip.detail.domain.BookDetailsRepository
import com.kontvip.detail.domain.BooksDetailUiFactory
import com.kontvip.detail.domain.FetchBookDetailsUseCase
import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.domain.model.DomainBooksDetail
import com.kontvip.detail.presentation.core.DefaultBooksDetailUiFactory
import com.kontvip.detail.presentation.core.DomainToBookDetailsUiStateMapper
import com.kontvip.detail.presentation.core.NoBooksResultToDetailScreenUiState
import com.kontvip.detail.presentation.core.SuccessResultToDetailScreenUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetailModule {

    @Provides
    @Singleton
    fun provideDetailRouteProvider(): DetailRouteProvider = DefaultDetailRouteProvider()

    @Provides
    @IntoSet
    fun provideDetailRouteBuilder(): RouteBuilder = DetailRouteBuilder()

    @Provides
    @Singleton
    fun provideBookDetailRepository(
        cacheToDomainBooksDetailMapper: CacheBook.Mapper<DomainBooksDetail>,
        booksDao: BooksDao
    ): BookDetailsRepository = DefaultBookDetailsRepository(
        cacheToDomainBooksDetailMapper = cacheToDomainBooksDetailMapper,
        booksDao = booksDao
    )

    @Provides
    @Singleton
    fun provideCacheToDomainBooksDetailMapper(): CacheBook.Mapper<DomainBooksDetail> =
        CacheToDomainBooksDetailMapper()

    @Provides
    @Singleton
    fun provideFetchBooksDetailUseCase(
        bookDetailsRepository: BookDetailsRepository,
        booksDetailUiFactory: BooksDetailUiFactory,
        dispatcherList: DispatcherList
    ): FetchBookDetailsUseCase = FetchBookDetailsUseCase.Default(
        bookDetailsRepository = bookDetailsRepository,
        booksDetailUiFactory = booksDetailUiFactory,
        dispatcherList = dispatcherList
    )

    @Provides
    @Singleton
    fun provideBooksDetailUiFactory(
        successResultToDetailScreenUiState: DetailResult.Success.Mapper<DetailScreenUiState>,
        noBooksResultToDetailScreenUiState: DetailResult.NoBookFound.Mapper<DetailScreenUiState>,
    ): BooksDetailUiFactory =
        DefaultBooksDetailUiFactory(
            successResultToDetailScreenUiState = successResultToDetailScreenUiState,
            noBooksResultToDetailScreenUiState = noBooksResultToDetailScreenUiState
        )

    @Provides
    @Singleton
    fun provideSuccessResultToDetailScreenUiState(
        domainToBookDetailsUiState: DomainBooksDetail.Mapper<DetailScreenUiState>
    ): DetailResult.Success.Mapper<DetailScreenUiState> = SuccessResultToDetailScreenUiState(
        domainToBookDetailsUiState = domainToBookDetailsUiState
    )

    @Provides
    @Singleton
    fun provideDomainToBookDetailsUiState(): DomainBooksDetail.Mapper<DetailScreenUiState> =
        DomainToBookDetailsUiStateMapper()

    @Provides
    @Singleton
    fun provideNoBooksResultToDetailScreenUiState(): DetailResult.NoBookFound.Mapper<DetailScreenUiState> =
        NoBooksResultToDetailScreenUiState()
}