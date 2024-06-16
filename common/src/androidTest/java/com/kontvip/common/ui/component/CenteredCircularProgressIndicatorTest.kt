package com.kontvip.common.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.kontvip.common.BaseComposeTest
import org.junit.Test

class CenteredCircularProgressIndicatorTest : BaseComposeTest() {

    @Test
    fun centeredCircularProgressIndicatorTest() {
        composeTestRule.setContent {
            CenteredCircularProgressIndicator()
        }

        composeTestRule
            .onNodeWithTag("CenteredCircularProgressIndicator")
            .assertIsDisplayed()
    }
}