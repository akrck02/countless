package org.akrck02.countless.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.akrck02.countless.data.model.option.Direction


const val DEFAULT_ANIM_SPEED = 350

fun AnimatedContentTransitionScope<NavBackStackEntry>.getEnterTransition(direction: Direction? = null): EnterTransition {
    val direction = direction ?: getNavigationAnimationDirection(initialState.destination.route ?: "", targetState.destination.route ?: "")
    return slideIntoContainer(
        if (Direction.RIGHT == direction) AnimatedContentTransitionScope.SlideDirection.Right else AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(DEFAULT_ANIM_SPEED)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.getExitTransition(direction: Direction? = null): ExitTransition {
    val direction = direction ?: getNavigationAnimationDirection(initialState.destination.route ?: "", targetState.destination.route ?: "")
    return slideOutOfContainer(
        if (Direction.RIGHT == direction) AnimatedContentTransitionScope.SlideDirection.Right else AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(DEFAULT_ANIM_SPEED)
    )
}

inline fun <reified T : Route> NavGraphBuilder.show(
    navController: NavHostController,
    enterDirection: Direction? = null,
    exitDirection: Direction? = null,
    crossinline content: @Composable (NavHostController) -> Unit,
) {
    composable<T>(
        enterTransition = { getEnterTransition(enterDirection) },
        exitTransition = { getExitTransition(exitDirection) }
    ) { content(navController) }
}
