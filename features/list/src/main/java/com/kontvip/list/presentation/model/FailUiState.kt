package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kontvip.list.domain.core.ListScreenUiState

data class FailUiState(private val errorMessage: String) : ListScreenUiState {
    @Composable
    override fun UiDisplay(onBookSelected: (bookId: String) -> Unit) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            text = errorMessage
        )
    }

    override fun isFail(): Boolean = true
}