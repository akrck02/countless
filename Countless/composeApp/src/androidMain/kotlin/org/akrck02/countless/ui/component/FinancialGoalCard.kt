package org.akrck02.countless.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.compose.LocalLifecycleOwner
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.financial_goal_current_goal
import countless.composeapp.generated.resources.financial_goal_estimated_saving
import countless.composeapp.generated.resources.financial_goal_estimated_time_template
import countless.composeapp.generated.resources.financial_goal_month_limit
import countless.composeapp.generated.resources.financial_goal_progress_template
import countless.composeapp.generated.resources.financial_goal_progress_text_template
import kotlinx.coroutines.delay
import org.akrck02.countless.data.extension.defaultDigitFormat
import org.akrck02.countless.data.model.data.FinancialGoal
import org.akrck02.countless.data.model.data.getCurrentProgress
import org.akrck02.countless.data.model.data.getCurrentProgressPercent
import org.akrck02.countless.data.model.data.getHumanReadableEstimatedTimeDate
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.ui.theme.MEDIUM_ROUNDED_SHAPE
import org.jetbrains.compose.resources.stringResource


@Composable
fun FinancialGoalCard(financialGoal: FinancialGoal, animationIndex: Int = 1) {

    var alpha by remember { mutableFloatStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        label = "alpha",
        animationSpec = tween(durationMillis = 250, easing = LinearEasing)
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .graphicsLayer(
                alpha = animatedAlpha
            )
    ) {

        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = MEDIUM_ROUNDED_SHAPE,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Text(
                    text = stringResource(Res.string.financial_goal_current_goal),
                    fontSize = 5.em,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)

                )


                Text(
                    text = financialGoal.name ?: "",
                    fontSize = 7.em,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.95f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)

                )

                Text(
                    text = stringResource(Res.string.financial_goal_progress_template, "${financialGoal.getCurrentProgressPercent()}%"),
                    fontSize = 5.em,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)

                )



                LinearProgressIndicator(
                    progress = { financialGoal.getCurrentProgress().toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .padding(bottom = 10.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    strokeCap = StrokeCap.Round,
                )


                Text(
                    text = stringResource(
                        Res.string.financial_goal_progress_text_template,
                        "${financialGoal.currentValue.defaultDigitFormat()}€",
                        "${financialGoal.targetValue.defaultDigitFormat()}€"
                    ),
                    fontSize = 5.em,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)

                )

                Text(
                    text = stringResource(
                        Res.string.financial_goal_estimated_time_template,
                        financialGoal.getHumanReadableEstimatedTimeDate()
                    ),
                    fontSize = 5.em,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)

                )
            }
        }

        Text(
            text = stringResource(Res.string.financial_goal_month_limit),
            fontSize = 5.em,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    bottom = 2.dp,
                    start = 25.dp,
                    end = 25.dp
                )

        )

        Text(
            text = financialGoal.monthSavings.defaultDigitFormat(),
            fontSize = 5.em,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 0.dp,
                    bottom = 15.dp,
                    start = 25.dp,
                    end = 25.dp
                )

        )

        Text(
            text = stringResource(Res.string.financial_goal_estimated_saving),
            fontSize = 5.em,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onSurfaceVariant.modify(.5f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 2.dp,
                    start = 25.dp,
                    end = 25.dp
                )

        )

        Text(
            text = financialGoal.monthSpendLimit.defaultDigitFormat(),
            fontSize = 5.em,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 0.dp,
                    bottom = 15.dp,
                    start = 25.dp,
                    end = 25.dp
                )

        )

    }


    LaunchedEffect(LocalLifecycleOwner.current) {
        val delay = 150L + animationIndex * 100
        delay(delay)
        alpha = 1f
    }
}
