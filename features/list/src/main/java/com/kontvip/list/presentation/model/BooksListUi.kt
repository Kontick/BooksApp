package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

sealed interface BooksListUi {

    @Composable
    fun UiDisplay()

    class Success(private val books: List<String>) : BooksListUi {
        @Composable
        override fun UiDisplay() {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(books) { book ->
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = book
                    )
                }
            }
        }
    }

    class Fail(private val errorMessage: String) : BooksListUi {
        @Composable
        override fun UiDisplay() {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = errorMessage
            )
        }
    }

    data object Loading : BooksListUi {
        @Composable
        override fun UiDisplay() {
            CircularProgressIndicator()
        }
    }

}