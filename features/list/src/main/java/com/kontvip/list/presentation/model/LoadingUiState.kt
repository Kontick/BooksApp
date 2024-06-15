package com.kontvip.list.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kontvip.common.ui.CenteredCircularProgressIndicator
import com.kontvip.list.domain.core.ListScreenUiState

data object LoadingUiState : ListScreenUiState {
    @Composable
    override fun UiDisplay(onBookSelected: (bookId: String) -> Unit) {
        CenteredCircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingUiStatePreview() {
    LoadingUiState.UiDisplay {}
}