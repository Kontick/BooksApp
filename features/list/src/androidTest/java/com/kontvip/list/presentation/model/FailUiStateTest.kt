package com.kontvip.list.presentation.model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.kontvip.list.BaseComposeTest
import org.junit.Assert.*
import org.junit.Test

class FailUiStateTest : BaseComposeTest() {
    @Test
    fun uiDisplayShowsErrorMessage() {
        val errorMessage = "Fail"
        val failUiState = FailUiState(errorMessage)

        var wasOnBookSelectedInvoked = false
        composeTestRule.setContent {
            failUiState.UiDisplay(onBookSelected = {
                wasOnBookSelectedInvoked = true
            })
        }

        composeTestRule.onNodeWithText("Fail")
            .assertExists()
            .assertIsDisplayed()

        assertFalse(wasOnBookSelectedInvoked)
    }

    @Test
    fun isFailReturnsTrue() {
        val failUiState = FailUiState("Fail")
        val result = failUiState.isFail()
        assertTrue(result)
    }
}