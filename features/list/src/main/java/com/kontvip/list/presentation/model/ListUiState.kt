package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.presentation.ListViewModel

class ListUiState(private val books: List<BookUi>) : ListScreenUiState {
    @Composable
    override fun UiDisplay(viewModel: ListViewModel) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(books) { book ->
                book.UiDisplay(viewModel)
            }
        }
    }

    override fun canBeDisplayed(): Boolean {
        return books.isNotEmpty()
    }
}