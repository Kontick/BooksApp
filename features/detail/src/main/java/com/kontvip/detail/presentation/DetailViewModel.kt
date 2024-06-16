package com.kontvip.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kontvip.common.core.DispatcherList
import com.kontvip.detail.domain.FetchBookDetailsUseCase
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.model.LoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DetailViewModel {

    fun loadBookWithId(bookId: String) = Unit
    fun detailScreenUiStateFlow(): StateFlow<DetailScreenUiState>

    @HiltViewModel
    class Default @Inject constructor(
        private val fetchBookDetailsUseCase: FetchBookDetailsUseCase,
        private val dispatcherList: DispatcherList
    ) : ViewModel(), DetailViewModel {

        private val detailScreenUiStateFlow = MutableStateFlow<DetailScreenUiState>(LoadingUiState)

        override fun loadBookWithId(bookId: String) {
            viewModelScope.launch(dispatcherList.io()) {
                detailScreenUiStateFlow.value = fetchBookDetailsUseCase.invoke(bookId)
            }
        }

        override fun detailScreenUiStateFlow(): StateFlow<DetailScreenUiState> = detailScreenUiStateFlow
    }

}