package com.kontvip.list.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.kontvip.list.BaseComposeTest
import org.junit.Test

class BookDetailsKtTest : BaseComposeTest() {
    @Test
    fun bookDetails_displaysTitleAndDescription() {
        val title = "Test Title"
        val description = "Test description."

        composeTestRule.setContent {
            BookDetails(title = title, description = description)
        }

        composeTestRule.onNodeWithText(title)
            .assertExists()
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(description)
            .assertExists()
            .assertIsDisplayed()
    }
}