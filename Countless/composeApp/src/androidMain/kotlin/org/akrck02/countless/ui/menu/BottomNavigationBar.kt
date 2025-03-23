package org.akrck02.countless.ui.menu

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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.navigation.GoalsRoute
import org.akrck02.countless.ui.navigation.ScheduleRoute
import org.akrck02.countless.ui.navigation.StatsRoute
import org.akrck02.countless.ui.navigation.WalletRoute
import org.akrck02.countless.ui.navigation.goalsRoute
import org.akrck02.countless.ui.navigation.navigateSecurely
import org.akrck02.countless.ui.navigation.scheduleRoute
import org.akrck02.countless.ui.navigation.statsRoute
import org.akrck02.countless.ui.navigation.walletRoute
import org.akrck02.countless.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.countless.viewmodel.AppViewModel

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(appViewModel: AppViewModel) {

    //initializing the default selected item
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    /**
     * by using the rememberNavController()
     * we can get the instance of the navController
     */
    val navController = rememberNavController()

    //scaffold to hold our bottom navigation Bar
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
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
                    val statsSelected = navigationSelectedItem == 0
                    BottomNavigationBarOption(
                        label = "Stats", // stringResource(Res.string.StatsOption),
                        icon = Icons.Rounded.BarChart,
                        selected = statsSelected,
                    ) {
                        navigationSelectedItem = 0
                        navController.navigateSecurely(StatsRoute.apply {
                            this.appViewModel = appViewModel
                        })
                    }

                    val walletSelected = navigationSelectedItem == 1
                    BottomNavigationBarOption(
                        label = "Wallet", //stringResource(Res.string.WalletOption),
                        icon = Icons.Rounded.Euro,
                        selected = walletSelected,
                    ) {
                        navigationSelectedItem = 1
                        navController.navigateSecurely(WalletRoute.apply {
                            this.appViewModel = appViewModel
                        })
                    }

                    val scheduleSelected = navigationSelectedItem == 2
                    BottomNavigationBarOption(
                        label = "Schedule", //stringResource(Res.string.ScheduleOption),
                        icon = Icons.Rounded.Schedule,
                        selected = scheduleSelected,
                    ) {
                        navigationSelectedItem = 2
                        navController.navigateSecurely(ScheduleRoute.apply {
                            this.appViewModel = appViewModel
                        })
                    }

                    val goalsSelected = navigationSelectedItem == 3
                    BottomNavigationBarOption(
                        label = "Goals", //stringResource(Res.string.GoalsOption),
                        icon = Icons.Rounded.Flag,
                        selected = goalsSelected,
                    ) {
                        navigationSelectedItem = 3
                        navController.navigateSecurely(GoalsRoute.apply {
                            this.appViewModel = appViewModel
                        })
                    }
                }
            }
        }
    ) { _ ->

        NavHost(
            navController = navController,
            startDestination = StatsRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) {

            statsRoute(navController)
            walletRoute(navController)
            scheduleRoute(navController)
            goalsRoute(navController)

        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomNavigationBarOption(
    label: String = "",
    selected: Boolean = false,
    icon: ImageVector = Icons.Rounded.Autorenew,
    onclick: () -> Unit
) {

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .size(80.dp),
        shape = DEFAULT_ROUNDED_SHAPE,

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                onclick()
            }
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary.takeIf { selected } ?: MaterialTheme.colorScheme.onSurface.modify(.4f),
                contentDescription = label
            )
            Text(
                text = label,
                color = MaterialTheme.colorScheme.primary.takeIf { selected } ?: MaterialTheme.colorScheme.onSurface.modify(.4f),
                fontSize = 3.em
            )
        }
    }
}