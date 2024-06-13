package com.kontvip.common.core

import android.content.Context
import androidx.annotation.StringRes

interface StringProvider {

    fun string(@StringRes stringRes: Int): String

    class Default(private val context: Context) : StringProvider {
        override fun string(stringRes: Int): String {
            return context.getString(stringRes)
        }
    }
}