package com.kontvip.list.presentation.component

import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.unit.dp
import com.kontvip.list.BaseComposeTest
import org.junit.Test

class BookCoverKtTest : BaseComposeTest() {
    @Test
    fun bookCover_displayedCorrectly() {
        composeTestRule.setContent {
            BookCover(imageUrl = "https://test.com")
        }

        composeTestRule.onNodeWithContentDescription(string(com.kontvip.common.R.string.book_cover))
            .assertExists()
            .assertIsDisplayed()
            .assertHeightIsEqualTo(60.dp)
    }
}