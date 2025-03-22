package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.akrck02.countless.navigation.route.StatsRoute

@Composable
fun GoalsView(
    navController: NavHostController
) {
    StatsRoute.dataAccessLayer.getAccountDataAccess().findAccount(1)
    Column(modifier = Modifier.fillMaxSize()) {

        Text("ESTAMO goals")
    }
}
