package com.kontvip.common.core

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Test

class DispatcherListTest {

    @Test
    fun `default dispatcher should return correct dispatchers`() {
        val dispatcherList = DispatcherList.Default()

        val uiDispatcher = dispatcherList.ui()
        val ioDispatcher = dispatcherList.io()

        assertEquals(Dispatchers.Main, uiDispatcher)
        assertEquals(Dispatchers.IO, ioDispatcher)
    }
}