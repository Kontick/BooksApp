package com.kontvip.detail.core

import com.kontvip.common.navigation.Route
import com.kontvip.common.navigation.DetailRouteProvider

class DefaultDetailRouteProvider : DetailRouteProvider {
    override fun route(id: String): Route = DetailRoute(id)
}