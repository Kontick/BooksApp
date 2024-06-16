package com.kontvip.common.core

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import com.kontvip.common.R
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringProviderTest {

    @Test
    fun string_returnsCorrectString() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val stringProvider = StringProvider.Default(context)

        @StringRes val testStringRes = R.string.back_button

        val expectedString = context.getString(testStringRes)
        val result = stringProvider.string(testStringRes)

        assertEquals(expectedString, result)
    }
}