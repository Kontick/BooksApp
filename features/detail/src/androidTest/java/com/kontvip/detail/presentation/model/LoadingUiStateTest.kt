package com.kontvip.detail.presentation.model

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.kontvip.common.ui.component.CenteredCircularProgressIndicator
import com.kontvip.detail.BaseComposeTest
import org.junit.Test

class LoadingUiStateTest : BaseComposeTest() {

    @Test
    fun loadingUiState_centeredCircularProgressIndicatorDisplayed() {
        composeTestRule.setContent {
            LoadingUiState.UiDisplay()
        }

        composeTestRule
            .onNodeWithTag("CenteredCircularProgressIndicator")
            .assertExists()
            .assertIsDisplayed()
    }

}