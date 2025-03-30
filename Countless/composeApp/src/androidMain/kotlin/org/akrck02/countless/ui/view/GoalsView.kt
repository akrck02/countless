@file:Suppress("unused")

package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.financial_goals_title
import org.akrck02.countless.ui.component.FinancialGoalCard
import org.akrck02.countless.ui.component.SectionTitle
import org.akrck02.countless.viewmodel.AppViewModel
import org.akrck02.countless.viewmodel.GoalsViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GoalsView(
    appViewModel: AppViewModel,
    viewModel: GoalsViewModel = koinViewModel()
) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(
                top = 10.dp,
                bottom = 110.dp,
                start = 30.dp,
                end = 30.dp
            )
    ) {

        SectionTitle(
            stringResource(Res.string.financial_goals_title),
            Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 30.dp)
        )

        appViewModel.currentFinancialGoal?.also {
            FinancialGoalCard(it)
        }
    }
}

