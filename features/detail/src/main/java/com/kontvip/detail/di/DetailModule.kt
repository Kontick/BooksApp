package com.kontvip.detail.di

import com.kontvip.common.navigation.DetailRouteProvider
import com.kontvip.common.navigation.RouteBuilder
import com.kontvip.detail.core.DefaultDetailRouteProvider
import com.kontvip.detail.core.DetailRouteBuilder
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

}