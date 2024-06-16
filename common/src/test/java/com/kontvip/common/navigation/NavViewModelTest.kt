package com.kontvip.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class NavViewModelTest {

    @Test
    fun `verify all route builders are called`() {
        var buildCalledCount = 0
        val testRouteBuilders = setOf(
            object : RouteBuilder {
                override fun build(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
                    buildCalledCount++
                }
            },
            object : RouteBuilder {
                override fun build(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
                    buildCalledCount++
                }
            },
            object : RouteBuilder {
                override fun build(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
                    buildCalledCount++
                }
            },
        )

        val viewModel = NavViewModel(testRouteBuilders)
        viewModel.buildRoutes(mock(), mock())

        assertEquals(testRouteBuilders.size, buildCalledCount)
    }
}