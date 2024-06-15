package com.kontvip.list.presentation.core

import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.domain.model.ListResult
import com.kontvip.list.presentation.model.FailUiState

class FailResultToListScreenUiState : ListResult.Fail.Mapper<ListScreenUiState> {
    override fun map(errorMessage: String): ListScreenUiState {
        return FailUiState(errorMessage)
    }
}