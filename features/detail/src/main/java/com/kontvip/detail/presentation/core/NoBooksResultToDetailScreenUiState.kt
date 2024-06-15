package com.kontvip.detail.presentation.core

import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.model.NoBookFoundUiState

class NoBooksResultToDetailScreenUiState : DetailResult.NoBookFound.Mapper<DetailScreenUiState> {
    override fun map(): DetailScreenUiState {
        return NoBookFoundUiState
    }
}