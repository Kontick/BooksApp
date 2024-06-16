package com.kontvip.list.core

import com.kontvip.common.navigation.Route
import com.kontvip.common.navigation.ListRouteProvider

class DefaultListRouteProvider : ListRouteProvider {
    override fun route(): Route = ListRoute
}