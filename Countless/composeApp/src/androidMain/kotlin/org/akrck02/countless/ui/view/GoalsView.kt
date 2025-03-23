@file:Suppress("unused")

package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.akrck02.countless.viewmodel.GoalsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun GoalsView(
    navController: NavHostController,
    viewModel: GoalsViewModel = koinViewModel()
) {

    val timer = viewModel.timer
    Column(modifier = Modifier.fillMaxSize()) {

        Text("ESTAMO goals $timer")
    }
}
