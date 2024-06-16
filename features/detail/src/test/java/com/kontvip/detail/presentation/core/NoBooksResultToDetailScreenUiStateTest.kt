package com.kontvip.detail.presentation.core

import com.kontvip.detail.presentation.model.NoBookFoundUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class NoBooksResultToDetailScreenUiStateTest {
    @Test
    fun `map create NoBookFoundUiState`() {
        val actual = NoBooksResultToDetailScreenUiState().map()
        val expected = NoBookFoundUiState
        assertEquals(expected, actual)
    }
}