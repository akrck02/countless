package org.akrck02.countless.ui.view

import android.icu.text.DateFormat
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EventAvailable
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.stats_ahead_of_budget_template
import countless.composeapp.generated.resources.stats_savings_objective_title_template
import countless.composeapp.generated.resources.stats_savings_title
import countless.composeapp.generated.resources.stats_spend_more_template
import org.akrck02.countless.data.extension.asDate
import org.akrck02.countless.data.extension.defaultDigitFormat
import org.akrck02.countless.data.model.data.getCurrentProgress
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.theme.MIN_ROUNDED_SHAPE
import org.akrck02.countless.viewmodel.StatsViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel
import kotlin.math.abs

@Composable
fun StatsView(
    navController: NavHostController,
    viewModel: StatsViewModel = koinViewModel()
) {

    Column(
        verticalArrangement = Arrangement.Center,
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
        Column(
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = stringResource(Res.string.stats_savings_title),
                fontSize = 8.em,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)

            )
            Text(
                text = "${viewModel.currentFinancialGoal.currentValue.defaultDigitFormat()}€",
                fontSize = 15.em,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            Text(
                text = stringResource(Res.string.stats_savings_objective_title_template, "${viewModel.currentFinancialGoal.targetValue.defaultDigitFormat()}€"),
                fontSize = 8.em,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

            var progress by remember { mutableFloatStateOf(0f) }
            val progressAnimation by animateFloatAsState(
                targetValue = progress,
                animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            )

            LinearProgressIndicator(
                progress = { progressAnimation },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 20.dp, bottom = 10.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceContainerLow,
                strokeCap = StrokeCap.Round,
            )

            LaunchedEffect(LocalLifecycleOwner.current) {
                progress = viewModel.currentFinancialGoal.getCurrentProgress().toFloat()
            }

            val budgetDifference = viewModel.getBudgetDifference()
            Text(
                text = if (budgetDifference > 0.0) stringResource(Res.string.stats_ahead_of_budget_template, "${budgetDifference.defaultDigitFormat()}€")
                else stringResource(Res.string.stats_ahead_of_budget_template, "${abs(budgetDifference).defaultDigitFormat()}€"),
                fontSize = 3.em,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

            val budgetExcess = viewModel.getMonthBudgetExcess()

            Text(
                text = if (budgetExcess > 0.0) stringResource(Res.string.stats_spend_more_template, "${budgetExcess.defaultDigitFormat()}€") else "",
                fontSize = 4.em,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, bottom = 10.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = MIN_ROUNDED_SHAPE,
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier
                        .padding(5.dp),
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.EventAvailable,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                            contentDescription = "Estimated time"
                        )

                        val estimatedTimestamp = viewModel.currentFinancialGoal.estimatedTimestamp
                        Text(
                            text = "${estimatedTimestamp.asDate(DateFormat.MEDIUM)}.",
                            fontSize = 4.em,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                            modifier = Modifier.padding(start = 6.dp, bottom = 0.dp)
                        )
                    }
                }
            }

        }
    }
}
