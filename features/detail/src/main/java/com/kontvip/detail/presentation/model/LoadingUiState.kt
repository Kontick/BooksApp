package com.kontvip.detail.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kontvip.common.ui.CenteredCircularProgressIndicator
import com.kontvip.detail.domain.model.DetailScreenUiState

data object LoadingUiState : DetailScreenUiState {
    @Composable
    override fun UiDisplay() {
        CenteredCircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingUiStatePreview() {
    LoadingUiState.UiDisplay()
}