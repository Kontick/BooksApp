package com.kontvip.detail.domain.model

data class DomainBooksDetail(
    private val title: String,
    private val description: String,
    private val author: String,
    private val releaseDate: String,
    private val imageUrl: String
) {

    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(title, description, author, releaseDate, imageUrl)
    }

    interface Mapper<T> {
        fun map(
            title: String, description: String, author: String,
            releaseDate: String, imageUrl: String
        ): T
    }
}