package org.akrck02.countless.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.income_title
import countless.composeapp.generated.resources.month_selector_option
import countless.composeapp.generated.resources.outcome_title
import countless.composeapp.generated.resources.schedule_title
import countless.composeapp.generated.resources.year_selector_option
import org.akrck02.countless.ui.component.MinimalInfoCard
import org.akrck02.countless.ui.component.SectionTitle
import org.akrck02.countless.ui.component.TabBar
import org.akrck02.countless.viewmodel.ScheduleViewModel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel


enum class Period(val resource: StringResource) {
    Month(Res.string.month_selector_option),
    Year(Res.string.year_selector_option);

    suspend fun getName(): String {
        return getString(resource)
    }
}

@Composable
fun ScheduleView(
    navController: NavHostController,
    viewModel: ScheduleViewModel = koinViewModel()
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
            )
    ) {
        SectionTitle(
            text = stringResource(Res.string.schedule_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, bottom = 10.dp)
        )

        var selected by remember { mutableStateOf(Period.Month) }
        val options = mapOf(
            Pair(stringResource(Period.Year.resource), Period.Year),
            Pair(stringResource(Period.Month.resource), Period.Month)
        )

        TabBar<Period>(options, selected) { selected = it }
        when (selected) {
            Period.Month -> YearScheduledWallet()
            Period.Year -> MonthScheduledWallet()
        }

    }
}

@Composable
fun YearScheduledWallet() {
    Column(modifier = Modifier.padding(top = 35.dp)) {
        Row {
            MinimalInfoCard(stringResource(Res.string.income_title), "30,970€")
            MinimalInfoCard(stringResource(Res.string.outcome_title), "11,207.99€")
        }
    }
}

@Composable
fun MonthScheduledWallet() {
    Column(modifier = Modifier.padding(top = 35.dp)) {
        Row {
            MinimalInfoCard(stringResource(Res.string.income_title), "1,970€")
            MinimalInfoCard(stringResource(Res.string.outcome_title), "767.99€")
        }
    }
}