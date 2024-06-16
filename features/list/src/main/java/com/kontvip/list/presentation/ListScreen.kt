package com.kontvip.list.presentation

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
import com.kontvip.common.R
import com.kontvip.common.ui.component.BooksAppTopAppBar
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
    val uiState by viewModel.listScreenUiStateFlow().collectAsState()

    LaunchedEffect(true) {
        viewModel.loadBooksData()
    }

    Column(modifier = modifier.fillMaxSize()) {
        BooksAppTopAppBar(
            title = stringResource(id = R.string.app_name),
            showBackButton = false
        )
        uiState.UiDisplay(onBookSelected = { bookId ->
            viewModel.selectBook(navController, bookId)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    val fakeViewModel = object : ListViewModel {
        override fun listScreenUiStateFlow(): StateFlow<ListScreenUiState> {
            return MutableStateFlow(
                ListUiState(
                    listOf(
                        BookUi("1", "title1", "description1", ""),
                        BookUi("2", "title2", "description2", ""),
                        BookUi("3", "title3", "description3", ""),
                        BookUi("4", "title4", "description4", ""),
                        BookUi("5", "title5", "description5", ""),
                    )
                )
            )
        }
    }

    ListScreen(
        navController = rememberNavController(),
        modifier = Modifier,
        viewModel = fakeViewModel
    )
}