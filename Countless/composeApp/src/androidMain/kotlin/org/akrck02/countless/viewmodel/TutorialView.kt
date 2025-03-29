package org.akrck02.countless.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import countless.composeapp.generated.resources.tutorial_goal_example_month_savings
import countless.composeapp.generated.resources.tutorial_goal_example_month_spend_limit
import countless.composeapp.generated.resources.tutorial_goal_example_name
import countless.composeapp.generated.resources.tutorial_goal_example_value
import countless.composeapp.generated.resources.tutorial_goal_month_savings_label
import countless.composeapp.generated.resources.tutorial_goal_month_spend_limit_label
import countless.composeapp.generated.resources.tutorial_goal_name_label
import countless.composeapp.generated.resources.tutorial_goal_target_date_label
import countless.composeapp.generated.resources.tutorial_goal_value_label
import countless.composeapp.generated.resources.tutorial_greeting
import countless.composeapp.generated.resources.tutorial_start_account_creation
import countless.composeapp.generated.resources.tutorial_step_2_subtitle
import countless.composeapp.generated.resources.tutorial_step_2_title
import countless.composeapp.generated.resources.tutorial_step_3_subtitle
import countless.composeapp.generated.resources.tutorial_step_3_title
import org.akrck02.countless.ui.component.MaterialDatePicker
import org.akrck02.countless.ui.component.MaterialTextField
import org.akrck02.countless.ui.component.StepsBar
import org.akrck02.countless.ui.extension.modify
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TutorialView(appViewModel: AppViewModel, onFinish: () -> Unit) {

    var step by remember { mutableIntStateOf(0) }

    Scaffold(bottomBar = {
        Column(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            StepsBar(
                selected = step,
                maxSteps = 3
            )
        }

    }) {
        val bottomPadding = it.calculateBottomPadding()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {

            when (step) {
                0 -> Step1 { step++ }
                1 -> Step2 { step++ }
                2 -> Step3 { onFinish() }
            }

        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
private fun Step1(onNext: () -> Unit) {

    Text(
        text = stringResource(Res.string.tutorial_greeting),
        fontSize = 27.sp,
        fontWeight = FontWeight.Light,
        modifier = Modifier.padding(bottom = 10.dp)
    )

    Text(
        text = stringResource(Res.string.tutorial_start_account_creation),
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
    )

    Spacer(
        modifier = Modifier.padding(70.dp)
    )


    IconButton(
        shapes = IconButtonShapes(
            shape = MaterialTheme.shapes.extraLarge
        ),
        enabled = true,
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.surfaceDim,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = { onNext() },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.NavigateNext,
            contentDescription = "Next"
        )
    }

}


@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Step2(onNext: () -> Unit) {

    Text(
        text = stringResource(Res.string.tutorial_step_2_title),
        fontSize = 25.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurface.modify(0.8f),
        modifier = Modifier.padding(bottom = 10.dp)
    )
    Text(
        text = stringResource(Res.string.tutorial_step_2_subtitle),
        fontSize = 18.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
        modifier = Modifier.padding(bottom = 40.dp)
    )

    val exampleName = stringResource(Res.string.tutorial_goal_example_name)
    var name by remember { mutableStateOf(exampleName) }
    MaterialTextField(
        label = stringResource(Res.string.tutorial_goal_name_label),
        value = name,
        onValueChange = { name = it }
    )


    MaterialDatePicker(
        label = stringResource(Res.string.tutorial_goal_target_date_label),
        dialogAcceptLabel = "Ok",
        dialogDismissLabel = "Cancel"
    )

    val exampleValue = stringResource(Res.string.tutorial_goal_example_value)
    var value by remember { mutableStateOf(exampleValue) }
    MaterialTextField(
        type = KeyboardType.Number,
        label = stringResource(Res.string.tutorial_goal_value_label),
        value = value,
        onValueChange = { value = it }
    )

    Spacer(
        modifier = Modifier.padding(40.dp)
    )

    IconButton(
        shapes = IconButtonShapes(
            shape = MaterialTheme.shapes.extraLarge
        ),
        enabled = true,
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.surfaceDim,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = { onNext() },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.NavigateNext,
            contentDescription = "Next"
        )
    }

}


@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Step3(onNext: () -> Unit) {

    Text(
        text = stringResource(Res.string.tutorial_step_3_title),
        fontSize = 25.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurface.modify(0.8f),
        modifier = Modifier.padding(bottom = 10.dp)
    )
    Text(
        text = stringResource(Res.string.tutorial_step_3_subtitle),
        fontSize = 18.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
        modifier = Modifier.padding(bottom = 40.dp)
    )

    val monthSpendLimitExample = stringResource(Res.string.tutorial_goal_example_month_spend_limit)
    var monthSpend by remember { mutableStateOf(monthSpendLimitExample) }
    MaterialTextField(
        type = KeyboardType.Number,
        label = stringResource(Res.string.tutorial_goal_month_spend_limit_label),
        value = monthSpend,
        onValueChange = { monthSpend = it }
    )

    val monthSavingsExample = stringResource(Res.string.tutorial_goal_example_month_savings)
    var monthSavings by remember { mutableStateOf(monthSavingsExample) }
    MaterialTextField(
        type = KeyboardType.Number,
        label = stringResource(Res.string.tutorial_goal_month_savings_label),
        value = monthSavings,
        onValueChange = { monthSavings = it },
    )

    Spacer(
        modifier = Modifier.padding(40.dp)
    )

    IconButton(
        shapes = IconButtonShapes(
            shape = MaterialTheme.shapes.extraLarge
        ),
        enabled = true,
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.surfaceDim,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = { onNext() },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.NavigateNext,
            contentDescription = "Next"
        )
    }

}
