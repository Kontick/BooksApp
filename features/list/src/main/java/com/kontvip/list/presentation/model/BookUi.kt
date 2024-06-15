package com.kontvip.list.presentation.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kontvip.list.presentation.component.BookCover
import com.kontvip.list.presentation.component.BookDetails

data class BookUi(
    private val id: String,
    private val title: String,
    private val description: String,
    private val imageUrl: String
) {

    @Composable
    fun UiDisplay(onBookSelected: (bookId: String) -> Unit) {
        Row(modifier = Modifier
            .clickable { onBookSelected.invoke(id) }
            .padding(start = 16.dp, end = 16.dp)
        ) {
            BookCover(imageUrl = imageUrl)
            BookDetails(title = title, description = description)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BookUiPreview() {
    BookUi("1", "Book Title", "Book description", "").UiDisplay {}
}