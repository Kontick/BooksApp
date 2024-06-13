package com.kontvip.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kontvip.common.data.cache.model.CacheBook

@Database(entities = [CacheBook::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}