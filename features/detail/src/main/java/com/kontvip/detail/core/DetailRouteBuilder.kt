package com.kontvip.detail.core

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kontvip.common.navigation.RouteBuilder

class DetailRouteBuilder : RouteBuilder {
    override fun build(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<DetailRoute> {
            it.toRoute<DetailRoute>().Content(navController = navController, navBackStackEntry = it)
        }
    }
}