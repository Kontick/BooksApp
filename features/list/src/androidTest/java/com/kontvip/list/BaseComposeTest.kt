package com.kontvip.list

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule

abstract class BaseComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    protected val context: Context = InstrumentationRegistry.getInstrumentation().targetContext


    protected fun string(@StringRes stringRes: Int): String = context.getString(stringRes)
}