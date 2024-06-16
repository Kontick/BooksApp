package com.kontvip.common.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CenteredCircularProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize().testTag("CenteredCircularProgressIndicator"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun CenteredCircularProgressIndicatorPreview() {
    CenteredCircularProgressIndicator()
}