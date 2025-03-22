package org.akrck02.countless.navigation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.Serializable
import org.akrck02.countless.dal.DataAccessLayer
import org.akrck02.countless.model.Direction
import java.util.Objects

class Routes

@Serializable
open class Route

var current: Route? = null

// ROUTES
@Serializable
data object StatsRoute : Route() {
    lateinit var dataAccessLayer: DataAccessLayer
}

@Serializable
data object WalletRoute : Route() {
    lateinit var dataAccessLayer: DataAccessLayer
}

@Serializable
data object ScheduleRoute : Route() {
    lateinit var dataAccessLayer: DataAccessLayer
}

@Serializable
data object GoalsRoute : Route() {
    lateinit var dataAccessLayer: DataAccessLayer
}

val availableRoutes = listOf(StatsRoute.getRoutePackageName(), WalletRoute.getRoutePackageName(), ScheduleRoute.getRoutePackageName(), GoalsRoute.getRoutePackageName())

/**
 * Get the animation direction for path
 */
fun getNavigationAnimationDirection(initialPath: String, targetPath: String): Direction {
    return if (availableRoutes.indexOf(initialPath) < availableRoutes.indexOf(targetPath)) Direction.LEFT else Direction.RIGHT
}

@Composable
fun NavHostController.getCurrentRoute(): String? {
    return this.currentBackStackEntryAsState().value?.destination?.route
}


/**
 * Navigate to a route skipping current one
 */
fun NavHostController.navigateSecurely(route: Route) {

    val currentRoutePackage = current?.getRoutePackageName()
    if (Objects.equals(route.getRoutePackageName(), currentRoutePackage))
        return

    current = route
    this.navigate(route)
}


/**
 * Get the package name of a Route object
 */
fun Route.getRoutePackageName(): String {
    return this.javaClass.kotlin.qualifiedName.toString()
}
