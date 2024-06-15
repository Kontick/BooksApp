package com.kontvip.detail.presentation.core

import com.kontvip.detail.domain.BooksDetailUiFactory
import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DetailScreenUiState

class DefaultBooksDetailUiFactory(
    private val successResultToDetailScreenUiState: DetailResult.Success.Mapper<DetailScreenUiState>,
    private val noBooksResultToDetailScreenUiState: DetailResult.NoBookFound.Mapper<DetailScreenUiState>,

    ) : BooksDetailUiFactory {
    override fun construct(detailResult: DetailResult): DetailScreenUiState {
        return detailResult.map(
            successResultToDetailScreenUiState, noBooksResultToDetailScreenUiState
        )
    }
}