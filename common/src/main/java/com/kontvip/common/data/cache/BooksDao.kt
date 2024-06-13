package com.kontvip.common.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kontvip.common.data.cache.model.CacheBook

@Dao
interface BooksDao {
    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: String): CacheBook?

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<CacheBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<CacheBook>)
}