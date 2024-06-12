package com.kontvip.list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel()
) {
    val uiState = viewModel.handle().collectAsState()
    Column(modifier = modifier.fillMaxSize()) {
        uiState.value.UiDisplay()
    }
}
