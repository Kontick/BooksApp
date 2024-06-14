package com.kontvip.booksapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kontvip.common.navigation.RouteBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavViewModel @Inject constructor(
    private val routeBuilders: Set<@JvmSuppressWildcards RouteBuilder>,
) : ViewModel() {
    fun buildRoutes(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        routeBuilders.forEach {
            it.build(navGraphBuilder = navGraphBuilder, navController = navController)
        }
    }
}

@Composable
fun NavHostMain(navController: NavHostController, startDestination: Any) {
    val viewModel = hiltViewModel<NavViewModel>()
    NavHost(navController = navController, startDestination = startDestination) {
        viewModel.buildRoutes(this, navController)
    }
}