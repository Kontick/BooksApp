package com.kontvip.common.navigation

interface DetailRouteProvider {
    fun route(id: String): Route
}

interface ListRouteProvider {
    fun route(): Route
}