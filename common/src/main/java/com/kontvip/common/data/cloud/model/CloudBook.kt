package com.kontvip.common.data.cloud.model

import com.google.gson.annotations.SerializedName

data class CloudBook(
    private val id: String? = null,
    private val title: String? = null,
    private val description: String? = null,
    private val author: String? = null,
    @SerializedName("release_date")
    private val releaseDate: String? = null,
    @SerializedName("image")
    private val imageUrl: String? = null
) {

    fun isValid(): Boolean = !id.isNullOrBlank()

    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(
            id ?: "",
            title ?: "",
            description ?: "",
            author ?: "",
            releaseDate ?: "",
            imageUrl ?: ""
        )
    }

    interface Mapper<T> {
        fun map(
            id: String, title: String, description: String,
            author: String, releaseDate: String, imageUrl: String
        ): T
    }
}