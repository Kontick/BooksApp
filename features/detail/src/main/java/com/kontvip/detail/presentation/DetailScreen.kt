package com.kontvip.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(
    navController: NavController,
    bookId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel<DetailViewModel.Default>()
) {
    val detailBook by viewModel.stateFlow().collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBookWithId(bookId)
    }

    Column(modifier = modifier.fillMaxSize()) {
        detailBook.UiDisplay()
    }
}

@Composable
@Preview
fun DetailScreenPreview() {
    DetailScreen(rememberNavController(), "1")
}