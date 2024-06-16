package com.kontvip.detail.presentation.core

import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.domain.model.DomainBooksDetail
import com.kontvip.detail.presentation.model.BookDetailsUiState

class DomainToBookDetailsUiStateMapper : DomainBooksDetail.Mapper<DetailScreenUiState> {
    override fun map(
        title: String, description: String, author: String,
        releaseDate: String, imageUrl: String
    ): DetailScreenUiState {
        return BookDetailsUiState(title, description, author, releaseDate, imageUrl)
    }
}