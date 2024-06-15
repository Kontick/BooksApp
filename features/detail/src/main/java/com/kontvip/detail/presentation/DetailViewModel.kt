package com.kontvip.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kontvip.common.core.DispatcherList
import com.kontvip.detail.domain.FetchBooksDetailUseCase
import com.kontvip.detail.domain.model.DetailScreenUiState
import com.kontvip.detail.presentation.model.LoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DetailViewModel {

    fun loadBookWithId(bookId: String)
    fun stateFlow(): StateFlow<DetailScreenUiState>

    @HiltViewModel
    class Default @Inject constructor(
        private val fetchBooksDetailUseCase: FetchBooksDetailUseCase,
        private val dispatcherList: DispatcherList
    ) : ViewModel(), DetailViewModel {

        private val mutableStateFlow = MutableStateFlow<DetailScreenUiState>(LoadingUiState)


        override fun loadBookWithId(bookId: String) {
            viewModelScope.launch(dispatcherList.io()) {
                mutableStateFlow.value = fetchBooksDetailUseCase.invoke(bookId)
            }
        }

        override fun stateFlow(): StateFlow<DetailScreenUiState> = mutableStateFlow
    }

}