package com.kontvip.list.presentation.model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.kontvip.list.BaseComposeTest
import org.junit.Assert.assertFalse
import org.junit.Test

class LoadingUiStateTest : BaseComposeTest() {
    @Test
    fun loadingUiState_centeredCircularProgressIndicatorDisplayed() {
        var wasOnBookSelectedInvoked = false
        composeTestRule.setContent {
            LoadingUiState.UiDisplay(onBookSelected = {
                wasOnBookSelectedInvoked = true
            })
        }

        composeTestRule
            .onNodeWithTag("CenteredCircularProgressIndicator")
            .assertExists()
            .assertIsDisplayed()

        assertFalse(wasOnBookSelectedInvoked)
    }
}