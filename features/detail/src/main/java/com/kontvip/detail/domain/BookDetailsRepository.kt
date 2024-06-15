package com.kontvip.detail.domain

import com.kontvip.detail.domain.model.DetailResult

interface BookDetailsRepository {
    suspend fun findBookWithId(bookId: String): DetailResult
}