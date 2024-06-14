package com.kontvip.list.core

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kontvip.common.navigation.RouteBuilder

class ListRouteBuilder : RouteBuilder {

    override fun build(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<ListRoute> {
            it.toRoute<ListRoute>().Content(navController, it)
        }
    }
}