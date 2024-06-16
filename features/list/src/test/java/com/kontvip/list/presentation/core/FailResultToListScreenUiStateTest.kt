package com.kontvip.list.presentation.core

import com.kontvip.list.presentation.model.FailUiState
import org.junit.Assert.*
import org.junit.Test

class FailResultToListScreenUiStateTest {
    @Test
    fun `map should create FailUiState`() {
        val errorMessage = "fail"
        val actual = FailResultToListScreenUiState().map(errorMessage)
        val expected = FailUiState(errorMessage)
        assertEquals(expected, actual)
    }
}