package com.kontvip.detail.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kontvip.common.navigation.Route
import com.kontvip.detail.presentation.DetailScreen
import kotlinx.serialization.Serializable

@Serializable
class DetailRoute(private val bookId: String) : Route {
    @Composable
    override fun Content(navController: NavController) {
        DetailScreen(navController, bookId)
    }
}