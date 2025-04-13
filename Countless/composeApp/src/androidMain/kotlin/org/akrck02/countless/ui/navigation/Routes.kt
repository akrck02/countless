@file:Suppress("unused")

package org.akrck02.countless.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import org.akrck02.countless.data.model.option.Direction
import org.akrck02.countless.viewmodel.AppViewModel
import java.util.LinkedList

// ROUTES
@Serializable
@SerialName("")
open class Route

@OptIn(InternalSerializationApi::class)
fun Route.serialName() = this::class.serializer().descriptor.serialName

@Serializable
@SerialName("/stats")
object StatsRoute : Route()

@Serializable
@SerialName("/wallet")
object WalletRoute : Route()

@Serializable
@SerialName("/goals")
object GoalsRoute : Route()

val availableRoutes = LinkedList(mutableListOf(StatsRoute, WalletRoute, GoalsRoute)).map { it.serialName() }

/**
 * Get the animation direction for path
 */
fun getNavigationAnimationDirection(initialPath: String, targetPath: String): Direction {
    return if (availableRoutes.indexOf(initialPath) < availableRoutes.indexOf(targetPath)) Direction.LEFT else Direction.RIGHT
}

/**
 * Navigate to a route skipping current one
 */
fun NavHostController.navigateSecurely(route: Route, appViewModel: AppViewModel) {

    if (route.serialName() == this.currentBackStackEntry?.destination?.route)
        return

    appViewModel.currentRoute = route
    this.navigate(route)
}

@Composable
fun NavHostController.getCurrentRoute(): String? {
    return this.currentBackStackEntryAsState().value?.destination?.route
}
