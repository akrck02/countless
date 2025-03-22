package org.akrck02.countless.ui.menu

import androidx.compose.foundation.Image
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.account_balance_wallet
import countless.composeapp.generated.resources.account_balance_wallet_selected
import countless.composeapp.generated.resources.flag
import countless.composeapp.generated.resources.flag_selected
import countless.composeapp.generated.resources.monitoring
import countless.composeapp.generated.resources.monitoring_selected
import countless.composeapp.generated.resources.schedule
import countless.composeapp.generated.resources.schedule_selected
import org.akrck02.countless.dal.DataAccessLayer
import org.akrck02.countless.navigation.route.GoalsRoute
import org.akrck02.countless.navigation.route.ScheduleRoute
import org.akrck02.countless.navigation.route.StatsRoute
import org.akrck02.countless.navigation.route.WalletRoute
import org.akrck02.countless.navigation.route.navigateSecurely
import org.akrck02.countless.ui.component.router.goalsRoute
import org.akrck02.countless.ui.component.router.scheduleRoute
import org.akrck02.countless.ui.component.router.statsRoute
import org.akrck02.countless.ui.component.router.walletRoute
import org.akrck02.countless.ui.theme.DEFAULT_BOTTOM_BAR_BG
import org.akrck02.countless.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.jetbrains.compose.resources.painterResource

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(dataAccess: DataAccessLayer) {

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
        bottomBar = {

            Surface(
                color = DEFAULT_BOTTOM_BAR_BG,
                contentColor = Color(0xFF505050),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        PaddingValues(
                            horizontal = 10.dp
                        )
                    )
                ) {
                    val statsSelected = navigationSelectedItem == 0
                    BottomNavigationBarOption(
                        label = "Stats", // stringResource(Res.string.StatsOption),
                        icon = painterResource(Res.drawable.monitoring_selected.takeIf { statsSelected } ?: Res.drawable.monitoring),
                        selected = statsSelected,
                    ) {
                        navigationSelectedItem = 0
                        navController.navigateSecurely(StatsRoute.apply {
                            dataAccessLayer = dataAccess
                        })
                    }

                    val walletSelected = navigationSelectedItem == 1
                    BottomNavigationBarOption(
                        label = "Wallet", //stringResource(Res.string.WalletOption),
                        icon = painterResource(Res.drawable.account_balance_wallet_selected.takeIf { walletSelected } ?: Res.drawable.account_balance_wallet),
                        selected = walletSelected,
                    ) {
                        navigationSelectedItem = 1
                        navController.navigateSecurely(WalletRoute.apply {
                            dataAccessLayer = dataAccess
                        })
                    }

                    val scheduleSelected = navigationSelectedItem == 2
                    BottomNavigationBarOption(
                        label = "Schedule", //stringResource(Res.string.ScheduleOption),
                        icon = painterResource(Res.drawable.schedule_selected.takeIf { scheduleSelected } ?: Res.drawable.schedule),
                        selected = scheduleSelected,
                    ) {
                        navigationSelectedItem = 2
                        navController.navigateSecurely(ScheduleRoute.apply {
                            dataAccessLayer = dataAccess
                        })
                    }

                    val goalsSelected = navigationSelectedItem == 3
                    BottomNavigationBarOption(
                        label = "Goals", //stringResource(Res.string.GoalsOption),
                        icon = painterResource(Res.drawable.flag_selected.takeIf { goalsSelected } ?: Res.drawable.flag),
                        selected = goalsSelected,
                    ) {
                        navigationSelectedItem = 3
                        navController.navigateSecurely(GoalsRoute.apply {
                            dataAccessLayer = dataAccess
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
    icon: Painter,
    selected: Boolean = false,
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
            Image(
                painter = icon,
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = label,
                color = Color(0xFFFFFFFF.takeIf { selected } ?: 0xFF505050),
                fontSize = 3.em
            )
        }
    }
}