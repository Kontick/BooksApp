package com.kontvip.list.domain.core

import androidx.compose.runtime.Composable

interface ListScreenUiState {
    @Composable
    fun UiDisplay()

    fun canBeDisplayed(): Boolean = true
}