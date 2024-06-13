package com.kontvip.list.domain.model

data class DomainListBook(
    private val id: String,
    private val title: String,
    private val description: String,
    private val imageUrl: String
) {

    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(id, title, description, imageUrl)
    }

    interface Mapper<T> {
        fun map(id: String, title: String, description: String, imageUrl: String): T
    }
}