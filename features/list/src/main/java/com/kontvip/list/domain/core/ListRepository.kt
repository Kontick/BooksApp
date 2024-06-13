package com.kontvip.list.domain.core

import com.kontvip.list.domain.model.ListResult

interface ListRepository {
    suspend fun fetchBooksFromCloud(requestRepeatCount: Int = 0): ListResult

    fun getCachedBooks(): ListResult
}