package com.kontvip.list.presentation.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.presentation.ListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class BookUi(
    private val id: String,
    private val title: String,
    private val description: String,
    private val imageUrl: String
) {

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun UiDisplay(viewModel: ListViewModel) {
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            GlideImage(
                model = imageUrl,
                contentDescription = stringResource(id = com.kontvip.common.R.string.book_cover),
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
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
    val fakeViewModel = object : ListViewModel {
        override fun loadBooksData() = Unit
        override fun stateFlow(): StateFlow<ListScreenUiState> = MutableStateFlow(LoadingUiState)
        override fun navigateToDetail(bookId: String, navController: NavController) = Unit
    }
    BookUi("1", "Book Title", "Book description", "").UiDisplay(fakeViewModel)
}