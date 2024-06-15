package com.kontvip.detail.presentation.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.components.AuthorText
import com.kontvip.detail.presentation.components.BodyText
import com.kontvip.detail.presentation.components.HeaderImage
import com.kontvip.detail.presentation.components.TitleAndDate

data class BookDetailsUiState(
    private val title: String,
    private val description: String,
    private val author: String,
    private val releaseDate: String,
    private val imageUrl: String
) : DetailScreenUiState {

    @Composable
    override fun UiDisplay() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderImage(imageUrl)
            Spacer(modifier = Modifier.height(8.dp))
            TitleAndDate(title, releaseDate)
            Spacer(modifier = Modifier.height(8.dp))
            BodyText(description)
            Spacer(modifier = Modifier.height(8.dp))
            AuthorText(author)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BookDetailsUiStatePreview() {
    BookDetailsUiState(
        "title", "description", "Author", "12.09.2017", ""
    )
}