package com.kontvip.list.domain.model

sealed interface ListResult {

    fun shouldRequestAgain(): Boolean
    fun isSuccessful(): Boolean
    fun <T> map(successMapper: Success.Mapper<T>, failMapper: Fail.Mapper<T>): T

    data class Success(private val books: List<DomainListBook>) : ListResult {
        override fun shouldRequestAgain(): Boolean = false
        override fun isSuccessful(): Boolean = true

        override fun <T> map(successMapper: Mapper<T>, failMapper: Fail.Mapper<T>): T {
            return successMapper.map(books)
        }

        interface Mapper<T> {
            fun map(books: List<DomainListBook>): T
        }
    }

    data class Fail(
        private val errorMessage: String,
        private val shouldRequestAgain: Boolean
    ) : ListResult {
        override fun shouldRequestAgain(): Boolean = shouldRequestAgain
        override fun isSuccessful(): Boolean = false

        override fun <T> map(successMapper: Success.Mapper<T>, failMapper: Mapper<T>): T {
            return failMapper.map(errorMessage)
        }

        interface Mapper<T> {
            fun map(errorMessage: String): T
        }
    }

}