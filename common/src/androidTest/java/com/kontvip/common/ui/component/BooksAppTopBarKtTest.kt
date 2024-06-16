package com.kontvip.common.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.kontvip.common.BaseComposeTest
import com.kontvip.common.R
import org.junit.Test

class BooksAppTopAppBarUiTest : BaseComposeTest() {

    @Test
    fun titleIsDisplayed() {
        val titleText = "Test Title"
        composeTestRule.setContent {
            BooksAppTopAppBar(
                title = titleText,
                showBackButton = true
            )
        }

        composeTestRule.onNodeWithText(titleText).assertIsDisplayed()
    }

    @Test
    fun backButtonIsDisplayedWhenShowBackButtonIsTrue() {
        composeTestRule.setContent {
            BooksAppTopAppBar(
                title = "Test Title",
                showBackButton = true
            )
        }

        composeTestRule.onNodeWithContentDescription(string(R.string.back_button)).assertIsDisplayed()
    }

    @Test
    fun backButtonIsNotDisplayedWhenShowBackButtonIsFalse() {
        composeTestRule.setContent {
            BooksAppTopAppBar(
                title = "Test Title",
                showBackButton = false
            )
        }

        composeTestRule.onNodeWithContentDescription(string(R.string.back_button)).assertDoesNotExist()
    }

    @Test
    fun backButtonClickTriggersOnBackPressed() {
        var wasBackPressed = false
        composeTestRule.setContent {
            BooksAppTopAppBar(
                title = "Test Title",
                showBackButton = true,
                onBackPressed = { wasBackPressed = true }
            )
        }
        InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.onNodeWithContentDescription(string(R.string.back_button)).performClick()
        assert(wasBackPressed)
    }
}