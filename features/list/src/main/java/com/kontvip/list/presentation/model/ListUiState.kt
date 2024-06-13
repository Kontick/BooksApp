package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kontvip.list.domain.core.ListScreenUiState

class ListUiState(private val books: List<BookUi>) : ListScreenUiState {
    @Composable
    override fun UiDisplay() {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(books) { book ->
                book.UiDisplay()
            }
        }
    }

    override fun canBeDisplayed(): Boolean {
        return books.isNotEmpty()
    }
}