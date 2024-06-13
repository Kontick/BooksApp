package com.kontvip.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kontvip.common.core.DispatcherList
import com.kontvip.list.domain.FetchBookUseCase
import com.kontvip.list.domain.core.ListScreenUiState
import com.kontvip.list.presentation.model.LoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ListViewModel {

    fun loadBooksData()
    fun stateFlow(): StateFlow<ListScreenUiState>

    @HiltViewModel
    class Default @Inject constructor(
        private val fetchBookUseCase: FetchBookUseCase,
        private val dispatcherList: DispatcherList
    ) : ViewModel(), ListViewModel {

        private val mutableStateFlow = MutableStateFlow<ListScreenUiState>(LoadingUiState)

        override fun loadBooksData() {
            viewModelScope.launch(dispatcherList.ui()) {
                fetchBookUseCase.invoke {
                    mutableStateFlow.value = it
                }
            }
        }

        override fun stateFlow(): StateFlow<ListScreenUiState> = mutableStateFlow

    }
}