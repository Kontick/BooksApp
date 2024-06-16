package com.kontvip.detail.presentation.component

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.kontvip.detail.BaseComposeTest
import org.junit.Test

class AuthorTextTest : BaseComposeTest() {
    @Test
    fun authorText_displayedCorrectly() {
        composeTestRule.setContent {
            AuthorText(author = "Test Author")
        }

        composeTestRule.onNodeWithText(string(com.kontvip.common.R.string.author), substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("Test Author", substring = true)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun authorText_modifierApplied() {
        composeTestRule.setContent {
            AuthorText(author = "Test Author", modifier = Modifier.width(100.dp))
        }

        val padding = 32.dp
        composeTestRule.onNodeWithTag("AuthorTextRow")
            .assertExists()
            .assertWidthIsAtLeast(100.dp - padding)
    }
}