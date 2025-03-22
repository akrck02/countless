package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ScheduleView(
    navController: NavHostController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("ESTAMO Schedule")
    }
}
