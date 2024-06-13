package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

data class BookUi(
    private val id: String,
    private val title: String,
    private val description: String,
    private val imageUrl: String
) {

    @Composable
    fun UiDisplay() {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = title
        )
    }

}