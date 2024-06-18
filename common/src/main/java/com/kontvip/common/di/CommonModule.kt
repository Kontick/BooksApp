package com.kontvip.common.di

import android.content.Context
import androidx.room.Room
import com.kontvip.common.core.DispatcherList
import com.kontvip.common.core.StringProvider
import com.kontvip.common.data.cache.BooksDao
import com.kontvip.common.data.cache.BooksDatabase
import com.kontvip.common.data.cloud.BooksApi
import com.kontvip.common.data.security.PassphraseGenerator
import com.kontvip.common.data.security.BooksDatabasePassphrase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideBooksDatabasePassphrase(
        @ApplicationContext context: Context,
        passphraseGenerator: PassphraseGenerator
    ): BooksDatabasePassphrase = BooksDatabasePassphrase.Default(
        context = context,
        passphraseGenerator = passphraseGenerator
    )

    @Provides
    @Singleton
    fun provideSupportFactory(
        booksDatabasePassphrase: BooksDatabasePassphrase
    ): SupportFactory = SupportFactory(
        booksDatabasePassphrase.getPassphrase()
    )

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        supportFactory: SupportFactory
    ): BooksDatabase {
        return Room.databaseBuilder(context, BooksDatabase::class.java, "books_database")
            .openHelperFactory(supportFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksApi(retrofit: Retrofit): BooksApi = retrofit.create(BooksApi::class.java)

    @Provides
    @Singleton
    fun provideBooksDao(
        booksDatabase: BooksDatabase
    ): BooksDao = booksDatabase.booksDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://c27b2d72-8d9c-4aa0-b549-7ae7e5666815.mock.pstmn.io")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideDispatcherList(): DispatcherList = DispatcherList.Default()

    @Provides
    @Singleton
    fun provideStringProvider(
        @ApplicationContext context: Context
    ): StringProvider = StringProvider.Default(
        context = context
    )

    @Provides
    @Singleton
    fun providePassphraseGenerator(): PassphraseGenerator = PassphraseGenerator.Default()
}