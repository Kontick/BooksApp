package com.kontvip.detail.presentation.core

import com.kontvip.detail.domain.model.DetailResult
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.domain.model.DomainBooksDetail

class SuccessResultToDetailScreenUiState(
    private val domainToBookDetailsUiState: DomainBooksDetail.Mapper<DetailScreenUiState>
) : DetailResult.Success.Mapper<DetailScreenUiState> {
    override fun map(domainBooksDetail: DomainBooksDetail): DetailScreenUiState {
        return domainBooksDetail.map(domainToBookDetailsUiState)
    }
}