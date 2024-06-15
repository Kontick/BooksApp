package com.kontvip.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kontvip.common.navigation.popBackStackIfResumed
import com.kontvip.common.ui.BooksAppTopAppBar
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.model.LoadingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
        BooksAppTopAppBar(
            title = stringResource(id = com.kontvip.common.R.string.app_name),
            showBackButton = true
        ) {
            navController.popBackStackIfResumed()
        }
        detailBook.UiDisplay()
    }
}

@Composable
@Preview
fun DetailScreenPreview() {
    val fakeViewModel = object : DetailViewModel {
        override fun stateFlow(): StateFlow<DetailScreenUiState> {
            return MutableStateFlow(LoadingUiState)
        }
    }
    DetailScreen(rememberNavController(), "1", viewModel = fakeViewModel)
}