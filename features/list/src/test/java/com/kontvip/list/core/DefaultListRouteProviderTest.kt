package com.kontvip.list.core

import org.junit.Assert.*
import org.junit.Test

class DefaultListRouteProviderTest {
    @Test
    fun `ListRouteProvider route method should return ListRoute`() {
        val routeProvider = DefaultListRouteProvider()
        val actual = routeProvider.route()
        val expected = ListRoute
        assertEquals(expected, actual)
    }
}