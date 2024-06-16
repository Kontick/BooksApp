package com.kontvip.common.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Fields must be public because of Room
@Entity(tableName = "books")
data class CacheBook(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val author: String,
    val releaseDate: String,
    val dateInMillis: Long,
    val imageUrl: String
) {

    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(id, title, description, author, releaseDate, imageUrl)
    }

    interface Mapper<T> {
        fun map(
            id: String, title: String, description: String,
            author: String, releaseDate: String, imageUrl: String
        ): T
    }

}