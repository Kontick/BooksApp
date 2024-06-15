package com.kontvip.detail.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BodyText(bodyText: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        text = bodyText,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true)
@Composable
fun BodyTextPreview() {
    BodyText(bodyText = "Books are a uniquely portable magic.")
}