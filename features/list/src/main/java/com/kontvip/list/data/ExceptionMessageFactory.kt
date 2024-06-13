package com.kontvip.list.data

import com.kontvip.common.core.StringProvider
import java.net.UnknownHostException

interface ExceptionMessageFactory {
    fun map(exception: Throwable): String

    class Default(private val stringProvider: StringProvider) : ExceptionMessageFactory {
        override fun map(exception: Throwable): String {
            return stringProvider.string(
                when (exception) {
                    is InternalError -> com.kontvip.common.R.string.internal_error_message
                    is UnknownHostException -> com.kontvip.common.R.string.no_internet_connect_error_message
                    else -> com.kontvip.common.R.string.service_unavailable_error_message
                }
            )
        }
    }
}