package com.kontvip.detail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.kontvip.common.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeaderImage(imageUrl: String, modifier: Modifier = Modifier) {
    GlideImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.book_cover),
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        loading = placeholder(R.drawable.default_header),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun HeaderImagePreview(modifier: Modifier = Modifier) {
    HeaderImage(imageUrl = "", modifier = Modifier.background(Color.Red))
}