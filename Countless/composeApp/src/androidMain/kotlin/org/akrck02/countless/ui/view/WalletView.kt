package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.akrck02.countless.viewmodel.WalletViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WalletView(
    navController: NavHostController,
    viewModel: WalletViewModel = koinViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 50.dp,
                end = 50.dp
            )
    ) {


        Text("asDASDa")
    }
}
