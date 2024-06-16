package com.kontvip.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.kontvip.common.core.DispatcherList
import com.kontvip.common.navigation.DetailRouteProvider
import com.kontvip.common.navigation.navigateIfResumed
import com.kontvip.list.domain.FetchBooksUseCase
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.presentation.model.LoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ListViewModel {

    fun loadBooksData() = Unit
    fun listScreenUiStateFlow(): StateFlow<ListScreenUiState>
    fun selectBook(navController: NavController, bookId: String) = Unit

    @HiltViewModel
    class Default @Inject constructor(
        private val fetchBooksUseCase: FetchBooksUseCase,
        private val dispatcherList: DispatcherList,
        private val detailRouteProvider: DetailRouteProvider
    ) : ViewModel(), ListViewModel {

        private val listScreenUiStateFlow = MutableStateFlow<ListScreenUiState>(LoadingUiState)

        override fun loadBooksData() {
            viewModelScope.launch(dispatcherList.io()) {
                fetchBooksUseCase.invoke {
                    listScreenUiStateFlow.value = it
                }
            }
        }

        override fun listScreenUiStateFlow(): StateFlow<ListScreenUiState> = listScreenUiStateFlow

        override fun selectBook(navController: NavController, bookId: String) {
            navController.navigateIfResumed(detailRouteProvider.route(bookId))
        }
    }
}