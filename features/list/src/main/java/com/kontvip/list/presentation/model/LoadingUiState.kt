package com.kontvip.list.presentation.model

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.kontvip.list.domain.core.ListScreenUiState

data object LoadingUiState : ListScreenUiState {
    @Composable
    override fun UiDisplay() {
        CircularProgressIndicator()
    }
}