package com.kontvip.list.presentation

import androidx.lifecycle.ViewModel
import com.kontvip.list.presentation.model.BooksListUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class ListViewModel : ViewModel() {

    fun handle(): StateFlow<BooksListUi> {
        return MutableStateFlow(BooksListUi.Loading)
    }

}