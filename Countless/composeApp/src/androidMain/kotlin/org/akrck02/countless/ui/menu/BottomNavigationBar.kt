package org.akrck02.countless.ui.menu

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.configuration_option
import countless.composeapp.generated.resources.goals_option
import countless.composeapp.generated.resources.stats_option
import countless.composeapp.generated.resources.wallet_option
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.navigation.GoalsRoute
import org.akrck02.countless.ui.navigation.StatsRoute
import org.akrck02.countless.ui.navigation.WalletRoute
import org.akrck02.countless.ui.navigation.getCurrentRoute
import org.akrck02.countless.ui.navigation.navigateSecurely
import org.akrck02.countless.ui.navigation.serialName
import org.akrck02.countless.ui.navigation.show
import org.akrck02.countless.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.countless.ui.view.GoalsView
import org.akrck02.countless.ui.view.StatsView
import org.akrck02.countless.ui.view.WalletView
import org.akrck02.countless.viewmodel.AppViewModel
import org.jetbrains.compose.resources.stringResource


@Composable
fun BottomNavigationBar(appViewModel: AppViewModel) {

    //initializing the default selected item

    /**
     * by using the rememberNavController()
     * we can get the instance of the navController
     */
    val navController = rememberNavController()
    val selectedRoute = navController.getCurrentRoute()


    //scaffold to hold our bottom navigation Bar
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        PaddingValues(
                            start = 10.dp,
                            end = 10.dp,
                            bottom = 20.dp
                        )
                    )
                ) {

                    var statsSelected = false
                    var walletSelected = false
                    var goalsSelected = false
                    val configurationSelected = false

                    when (selectedRoute) {
                        StatsRoute.serialName() -> statsSelected = true
                        WalletRoute.serialName() -> walletSelected = true
                        GoalsRoute.serialName() -> goalsSelected = true
                        else -> statsSelected = true
                    }

                    BottomNavigationBarOption(
                        label = stringResource(Res.string.stats_option),
                        icon = Icons.Rounded.BarChart,
                        selected = statsSelected,
                    ) { navController.navigateSecurely(StatsRoute, appViewModel) }

                    BottomNavigationBarOption(
                        label = stringResource(Res.string.wallet_option),
                        icon = Icons.Rounded.Payments,
                        selected = walletSelected,
                    ) { navController.navigateSecurely(WalletRoute, appViewModel) }

                    BottomNavigationBarOption(
                        label = stringResource(Res.string.goals_option),
                        icon = Icons.Rounded.Flag,
                        selected = goalsSelected,
                    ) { navController.navigateSecurely(GoalsRoute, appViewModel) }

                    BottomNavigationBarOption(
                        label = stringResource(Res.string.configuration_option),
                        icon = Icons.Rounded.Tune,
                        selected = configurationSelected,
                    ) { /*navController.navigateSecurely(GoalsRoute) */ }

                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = StatsRoute,
            popExitTransition = {
                scaleOut(
                    targetScale = 0.8f,
                    transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 0.5f)
                )
            },
            popEnterTransition = { EnterTransition.None },
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {
            show<StatsRoute>(navController) {
                StatsView(appViewModel = appViewModel)
            }
            show<WalletRoute>(navController) {
                WalletView(appViewModel = appViewModel)
            }
            show<GoalsRoute>(navController) {
                GoalsView(appViewModel = appViewModel)
            }
        }

    }
}


@Composable
private fun BottomNavigationBarOption(
    label: String = "",
    selected: Boolean = false,
    icon: ImageVector = Icons.Rounded.Autorenew,
    color: Color = Color.Transparent,
    onclick: () -> Unit
) {

    Surface(
        color = color,
        modifier = Modifier
            .size(74.dp),
        shape = DEFAULT_ROUNDED_SHAPE
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                onclick()
            }
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary.takeIf { selected }
                    ?: MaterialTheme.colorScheme.onSurface.modify(.4f),
                contentDescription = label
            )
            Text(
                text = label,
                color = MaterialTheme.colorScheme.primary.takeIf { selected }
                    ?: MaterialTheme.colorScheme.onSurface.modify(.4f),
                fontSize = 11.sp,
            )
        }
    }
}