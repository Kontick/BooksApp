package com.kontvip.detail.presentation.component

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.kontvip.detail.BaseComposeTest
import org.junit.Test

class BodyTextTest : BaseComposeTest() {

    @Test
    fun bodyText_displayedCorrectly() {
        val bodyText = "Test body text"

        composeTestRule.setContent {
            BodyText(bodyText = bodyText)
        }

        composeTestRule.onNodeWithText(bodyText, substring = true)
            .assertExists()
            .assertTextEquals(bodyText)
            .isDisplayed()
    }

    @Test
    fun bodyText_modifierApplied() {
        composeTestRule.setContent {
            BodyText(bodyText = "bodyText", modifier = Modifier.width(200.dp))
        }

        val padding = 32.dp
        composeTestRule.onNodeWithText("bodyText")
            .assertExists()
            .assertWidthIsAtLeast(200.dp - padding)
    }
}