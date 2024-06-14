package com.kontvip.list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.presentation.model.BookUi
import com.kontvip.list.presentation.model.ListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel<ListViewModel.Default>()
) {
    val uiState by viewModel.stateFlow().collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBooksData()
    }

    Column(modifier = modifier.fillMaxSize()) {
        uiState.UiDisplay()
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    val fakeViewModel = object : ListViewModel {
        override fun stateFlow(): StateFlow<ListScreenUiState> {
            return MutableStateFlow(
                ListUiState(
                    listOf(
                        BookUi("1", "title", "description", "https://google.com"),
                        BookUi("2", "title2", "description", "https://google.com"),
                        BookUi("3", "title3", "description", "https://google.com"),
                        BookUi("4", "title4", "description", "https://google.com"),
                        BookUi("5", "title5", "description", "https://google.com"),
                    )
                )
            )
        }

        override fun loadBooksData() = Unit
        override fun navigateToDetail(bookId: String, navController: NavController) = Unit
    }

    ListScreen(
        navController = rememberNavController(),
        modifier = Modifier,
        viewModel = fakeViewModel
    )
}