package com.kontvip.list.domain.core

import androidx.compose.runtime.Composable
import com.kontvip.list.presentation.ListViewModel

interface ListScreenUiState {
    @Composable
    fun UiDisplay(onBookSelected: (bookId: String) -> Unit)

    fun canBeDisplayed(): Boolean = true
}