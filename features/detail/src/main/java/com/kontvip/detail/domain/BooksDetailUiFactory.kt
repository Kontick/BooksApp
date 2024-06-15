package com.kontvip.detail.domain

import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DetailScreenUiState

interface BooksDetailUiFactory {
    fun construct(detailResult: DetailResult): DetailScreenUiState
}