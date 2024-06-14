package com.kontvip.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

interface Route {
    @Composable
    fun Content(navController: NavController, navBackStackEntry: NavBackStackEntry)
}