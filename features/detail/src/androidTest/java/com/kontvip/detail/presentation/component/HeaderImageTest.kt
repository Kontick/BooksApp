package com.kontvip.detail.presentation.component

import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.unit.dp
import com.kontvip.detail.BaseComposeTest
import org.junit.Test

class HeaderImageTest : BaseComposeTest() {
    @Test
    fun headerImage_displayedCorrectly() {
        composeTestRule.setContent {
            HeaderImage(imageUrl = "https://test.com")
        }

        composeTestRule.onNodeWithContentDescription(string(com.kontvip.common.R.string.book_cover))
            .assertExists()
            .assertIsDisplayed()
            .assertHeightIsEqualTo(240.dp)
    }
}