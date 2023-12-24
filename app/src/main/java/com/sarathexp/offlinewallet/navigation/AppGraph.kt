package com.sarathexp.offlinewallet.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.sarathexp.offlinewallet.presentation.screen.destinations.AddCardDestination
import com.sarathexp.offlinewallet.presentation.screen.destinations.HomeDestination
import com.sarathexp.offlinewallet.presentation.screen.destinations.ListCardsDestination

object AppGraph {

    val card =
        object : NavGraphSpec {
            override val route = "card"
            override val startRoute = ListCardsDestination
            override val destinationsByRoute =
                listOf<DestinationSpec<*>>(
                    ListCardsDestination,
                    AddCardDestination
                ).associateBy { it.route }
        }

    val home =
        object : NavGraphSpec {
            override val route = "app_home"
            override val startRoute = card
            override val destinationsByRoute =
                listOf<DestinationSpec<*>>(
                    HomeDestination,
                ).associateBy { it.route }
            override val nestedNavGraphs = listOf(card)
        }

    // Root NavGraph
    val root =
        object : NavGraphSpec {
            override val route = "root"
            override val startRoute = home
            override val destinationsByRoute = listOf<DestinationSpec<*>>().associateBy { it.route }
            override val nestedNavGraphs = listOf(home)
        }
}
