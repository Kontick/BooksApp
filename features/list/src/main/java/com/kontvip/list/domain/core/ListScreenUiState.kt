package com.kontvip.list.domain.core

import androidx.compose.runtime.Composable

interface ListScreenUiState {
    @Composable
    fun UiDisplay(onBookSelected: (bookId: String) -> Unit)

    fun canBeDisplayed(): Boolean = true

    fun isFail(): Boolean = false
}