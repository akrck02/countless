package org.akrck02.countless.ui.component.router

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.akrck02.countless.model.Direction
import org.akrck02.countless.navigation.route.GoalsRoute
import org.akrck02.countless.navigation.route.ScheduleRoute
import org.akrck02.countless.navigation.route.StatsRoute
import org.akrck02.countless.navigation.route.WalletRoute
import org.akrck02.countless.navigation.route.getNavigationAnimationDirection
import org.akrck02.countless.ui.view.GoalsView
import org.akrck02.countless.ui.view.ScheduleView
import org.akrck02.countless.ui.view.StatsView
import org.akrck02.countless.ui.view.WalletView


const val DEFAULT_ANIM_SPEED = 350

private fun AnimatedContentTransitionScope<NavBackStackEntry>.getEnterTransition(): EnterTransition {
    val direction = getNavigationAnimationDirection(initialState.destination.route ?: "", targetState.destination.route ?: "")
    return slideIntoContainer(
        if (Direction.RIGHT == direction) AnimatedContentTransitionScope.SlideDirection.Right else AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(DEFAULT_ANIM_SPEED)
    )
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.getExitTransition(): ExitTransition {
    val direction = getNavigationAnimationDirection(initialState.destination.route ?: "", targetState.destination.route ?: "")
    return slideOutOfContainer(
        if (Direction.RIGHT == direction) AnimatedContentTransitionScope.SlideDirection.Right else AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(DEFAULT_ANIM_SPEED)
    )
}

fun NavGraphBuilder.statsRoute(
    navController: NavHostController,
) {
    composable<StatsRoute>(
        enterTransition = { getEnterTransition() },
        exitTransition = { getExitTransition() }
    ) { StatsView(navController) }
}


fun NavGraphBuilder.walletRoute(
    navController: NavHostController,
) {
    composable<WalletRoute>(
        enterTransition = { getEnterTransition() },
        exitTransition = { getExitTransition() }
    ) { WalletView(navController) }
}

fun NavGraphBuilder.scheduleRoute(
    navController: NavHostController,
) {
    composable<ScheduleRoute>(
        enterTransition = { getEnterTransition() },
        exitTransition = { getExitTransition() }
    ) { ScheduleView(navController) }
}

fun NavGraphBuilder.goalsRoute(
    navController: NavHostController,
) {
    composable<GoalsRoute>(
        enterTransition = { getEnterTransition() },
        exitTransition = { getExitTransition() }
    ) { GoalsView(navController) }
}