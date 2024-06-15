package com.kontvip.list.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookCover(imageUrl: String, modifier: Modifier = Modifier) {
    GlideImage(
        model = imageUrl,
        contentDescription = stringResource(id = com.kontvip.common.R.string.book_cover),
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        loading = placeholder(com.kontvip.common.R.drawable.default_book_cover)
    )
}


@Preview(showBackground = true)
@Composable
fun BookCoverPreview() {
    BookCover(imageUrl = "", modifier = Modifier.background(Color.Red))
}