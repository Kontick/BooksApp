package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.presentation.ListViewModel

data object LoadingUiState : ListScreenUiState {
    @Composable
    override fun UiDisplay(viewModel: ListViewModel) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}