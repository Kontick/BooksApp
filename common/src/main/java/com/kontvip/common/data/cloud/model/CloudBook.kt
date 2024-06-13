package com.kontvip.common.data.cloud.model

import com.google.gson.annotations.SerializedName

data class CloudBook(
    private val id: String,
    private val title: String,
    private val description: String,
    private val author: String,
    @SerializedName("release_date")
    private val releaseDate: String,
    @SerializedName("image")
    private val imageUrl: String
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