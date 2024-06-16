package com.kontvip.detail.core

import org.junit.Assert.*
import org.junit.Test

class DefaultDetailRouteProviderTest {

    @Test
    fun `DetailRouteProvider route method should return DetailRoute`() {
        val routeProvider = DefaultDetailRouteProvider()
        val actual = routeProvider.route("17")
        val expected = DetailRoute("17")
        assertEquals(expected, actual)
    }
}