package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kontvip.list.domain.core.ListScreenUiState

class ListUiState(private val books: List<BookUi>) : ListScreenUiState {
    @Composable
    override fun UiDisplay(onBookSelected: (bookId: String) -> Unit) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(books) { book ->
                Spacer(modifier = Modifier.height(8.dp))
                book.UiDisplay(onBookSelected)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    override fun canBeDisplayed(): Boolean {
        return books.isNotEmpty()
    }
}