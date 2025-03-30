package org.akrck02.countless.ui.view.tutorial.step

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.month_savings_must_be_positive
import countless.composeapp.generated.resources.month_spend_limit_must_be_positive
import countless.composeapp.generated.resources.tutorial_goal_example_month_savings
import countless.composeapp.generated.resources.tutorial_goal_example_month_spend_limit
import countless.composeapp.generated.resources.tutorial_goal_month_savings_label
import countless.composeapp.generated.resources.tutorial_goal_month_spend_limit_label
import countless.composeapp.generated.resources.tutorial_set_limits_subtitle
import countless.composeapp.generated.resources.tutorial_set_limits_title
import org.akrck02.countless.ui.component.MaterialTextField
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.viewmodel.TutorialViewModel
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GoalLimitsStep(tutorialViewModel: TutorialViewModel, showNextButton: (Boolean) -> Unit) {

    // values
    val monthSpendLimitExample = stringResource(Res.string.tutorial_goal_example_month_spend_limit)
    var monthSpend by remember { mutableStateOf(monthSpendLimitExample) }

    val monthSavingsExample = stringResource(Res.string.tutorial_goal_example_month_savings)
    var monthSavings by remember { mutableStateOf(monthSavingsExample) }

    // error messages
    val negativeMonthLimitError = stringResource(Res.string.month_spend_limit_must_be_positive)
    val negativeMonthSavingsError = stringResource(Res.string.month_savings_must_be_positive)
    var errorMessages = remember { mutableSetOf<String>() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = stringResource(Res.string.tutorial_set_limits_title),
            fontSize = 25.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.modify(0.8f),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = stringResource(Res.string.tutorial_set_limits_subtitle),
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        MaterialTextField(
            type = KeyboardType.Number,
            label = stringResource(Res.string.tutorial_goal_month_spend_limit_label),
            icon = Icons.Rounded.Payments,
            value = monthSpend,
            isError = errorMessages.contains(negativeMonthLimitError),
            onValueChange = {
                monthSpend = it.replace("-", "")
                val num = monthSpend.toDoubleOrNull()

                if (null == num || num <= 0.0) errorMessages.add(negativeMonthLimitError)
                else errorMessages.remove(negativeMonthLimitError)
            }
        )

        MaterialTextField(
            type = KeyboardType.Number,
            label = stringResource(Res.string.tutorial_goal_month_savings_label),
            icon = Icons.Rounded.Savings,
            value = monthSavings,
            isError = errorMessages.contains(negativeMonthSavingsError),
            onValueChange = {

                monthSavings = it.replace("-", "")
                val num = monthSavings.toDoubleOrNull()

                if (null == num || num <= 0.0) errorMessages.add(negativeMonthSavingsError)
                else errorMessages.remove(negativeMonthSavingsError)
            },
        )

        if (errorMessages.isNotEmpty()) {
            Text(
                text = errorMessages.first(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 40.dp)
            )
            showNextButton(false)
        } else {
            tutorialViewModel.financialGoal.apply {
                this.monthSavings = monthSavings.toDoubleOrNull() ?: 0.0
                this.monthSpendLimit = monthSpend.toDoubleOrNull() ?: 0.0
            }
            showNextButton(true)
        }

    }
}