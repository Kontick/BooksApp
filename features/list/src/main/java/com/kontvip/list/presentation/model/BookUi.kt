package com.kontvip.list.presentation.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

data class BookUi(
    private val id: String,
    private val title: String,
    private val description: String,
    private val imageUrl: String
) {

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun UiDisplay(onBookSelected: (bookId: String) -> Unit) {
        Row(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .clickable {
                onBookSelected.invoke(id)
            }) {
            GlideImage(
                model = imageUrl,
                contentDescription = stringResource(id = com.kontvip.common.R.string.book_cover),
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    text = title
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun BookUiPreview() {
    BookUi("1", "Book Title", "Book description", "").UiDisplay {}
}