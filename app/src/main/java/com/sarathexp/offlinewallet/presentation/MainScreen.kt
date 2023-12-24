package com.sarathexp.offlinewallet.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.sarathexp.offlinewallet.app.base.BaseViewModel
import com.sarathexp.offlinewallet.navigation.AppGraph
import com.sarathexp.offlinewallet.presentation.screen.card.interactor.CardViewModel

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {

    val navController = rememberAnimatedNavController()
    // TODO: Add Transition Animations
    val navHostEngine =
        rememberAnimatedNavHostEngine(rootDefaultAnimations = RootNavGraphDefaultAnimations())

    Scaffold { padding ->
        DestinationsNavHost(
            engine = navHostEngine,
            navController = navController,
            navGraph = AppGraph.root,
            modifier = modifier,
            dependenciesContainerBuilder = {
                provideViewModel<CardViewModel>(AppGraph.home)
            }
        )
    }
}


@Composable
inline fun <reified viewModel : BaseViewModel<*, *, *>> DependenciesContainerBuilder<*>
    .provideViewModel(
    navGraph: NavGraphSpec,
) {
    dependency(navGraph) {
        val parentEntry =
            remember(navBackStackEntry) { navController.getBackStackEntry(AppGraph.card.route) }
        hiltViewModel<viewModel>(parentEntry)
    }
}
