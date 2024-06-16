package com.kontvip.detail.domain.model

interface DetailResult {

    fun <T> map(successMapper: Success.Mapper<T>, failMapper: NoBookFound.Mapper<T>): T

    data class Success(private val domainBooksDetail: DomainBooksDetail) : DetailResult {
        override fun <T> map(successMapper: Mapper<T>, failMapper: NoBookFound.Mapper<T>): T {
            return successMapper.map(domainBooksDetail)
        }

        interface Mapper<T> {
            fun map(domainBooksDetail: DomainBooksDetail): T
        }
    }

    data object NoBookFound : DetailResult {
        override fun <T> map(successMapper: Success.Mapper<T>, failMapper: Mapper<T>): T {
            return failMapper.map()
        }

        interface Mapper<T> {
            fun map(): T
        }
    }

}