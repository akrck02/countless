package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.akrck02.countless.viewmodel.ScheduleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleView(
    navController: NavHostController,
    viewModel: ScheduleViewModel = koinViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("ESTAMO Schedule")
    }
}
