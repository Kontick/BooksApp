package com.kontvip.list.domain.core

import com.kontvip.list.domain.model.ListResult

interface ListRepository {
    suspend fun fetchBooksFromCloud(): ListResult

    fun getCachedBooks(): ListResult
}