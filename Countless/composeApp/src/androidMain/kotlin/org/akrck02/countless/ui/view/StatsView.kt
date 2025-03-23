package org.akrck02.countless.ui.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import org.akrck02.countless.data.extension.asDate
import org.akrck02.countless.data.extension.defaultDigitFormat
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.theme.TOTAL_ROUNDED_SHAPE
import org.akrck02.countless.viewmodel.StatsViewModel
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
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text(
                text = "Savings so far",
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
                text = "of ${viewModel.currentFinancialGoal.targetValue.defaultDigitFormat()}€",
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
                progress = viewModel.getCurrentProgress().toFloat()
            }

            val budgetDifference = viewModel.getBudgetDifference()
            Text(
                text = if (budgetDifference > 0.0) "${budgetDifference.defaultDigitFormat()}€ ahead of budget." else "${abs(budgetDifference).defaultDigitFormat()}€ behind budget.",
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
                text = if (budgetExcess > 0.0) "You can spend ${budgetExcess.defaultDigitFormat()}€ more this month." else "",
                fontSize = 4.em,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, bottom = 10.dp)
            )

            Surface(
                shape = TOTAL_ROUNDED_SHAPE,
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier
                    .padding(5.dp),
            ) {

                val estimatedTimestamp = viewModel.currentFinancialGoal.estimatedTimestamp
                Text(
                    text = "You will reach your financial goal on ${estimatedTimestamp.asDate()}.",
                    fontSize = 3.em,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp, bottom = 0.dp)
                )
            }
        }
    }
}
