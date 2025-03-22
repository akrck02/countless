package org.akrck02.countless.ui.menu

import androidx.compose.foundation.Image
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
import androidx.navigation.compose.rememberNavController
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.account_balance_wallet
import countless.composeapp.generated.resources.flag
import countless.composeapp.generated.resources.monitoring
import countless.composeapp.generated.resources.schedule
import org.akrck02.countless.ui.theme.DEFAULT_BOTTOM_BAR_BG
import org.jetbrains.compose.resources.painterResource

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {
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
                    BottomNavigationBarOption(
                        label = "Stats", //stringResource(Res.string.StatsOption),
                        route = "",
                        icon = painterResource(Res.drawable.monitoring)
                    )

                    BottomNavigationBarOption(
                        label = "Wallet", //stringResource(Res.string.WalletOption),
                        route = "",
                        icon = painterResource(Res.drawable.account_balance_wallet)
                    )

                    BottomNavigationBarOption(
                        label = "Schedule", //stringResource(Res.string.ScheduleOption),
                        route = "",
                        icon = painterResource(Res.drawable.schedule)
                    )

                    BottomNavigationBarOption(
                        label = "Goals", //stringResource(Res.string.GoalsOption),
                        route = "",
                        icon = painterResource(Res.drawable.flag)
                    )
                }
            }

        }
    ) { _ ->
        //We need to setup our NavHost in here
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomNavigationBarOption(
    label: String = "",
    icon: Painter,
    route: String = ""
) {

    Surface(
        color = Color.Transparent,
        modifier = Modifier.size(80.dp),
        onClick = {}
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = icon,
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = label,
                fontSize = 3.em
            )
        }
    }
}