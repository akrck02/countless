package org.akrck02.countless.ui.view.tutorial.step

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.material.icons.rounded.NewLabel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import countless.composeapp.generated.resources.date_must_be_future
import countless.composeapp.generated.resources.name_must_not_be_blank
import countless.composeapp.generated.resources.price_must_be_positive
import countless.composeapp.generated.resources.tutorial_create_goal_subtitle
import countless.composeapp.generated.resources.tutorial_create_goal_title
import countless.composeapp.generated.resources.tutorial_goal_example_name
import countless.composeapp.generated.resources.tutorial_goal_example_value
import countless.composeapp.generated.resources.tutorial_goal_name_label
import countless.composeapp.generated.resources.tutorial_goal_target_date_label
import countless.composeapp.generated.resources.tutorial_goal_value_label
import org.akrck02.countless.ui.component.MaterialDatePicker
import org.akrck02.countless.ui.component.MaterialTextField
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.viewmodel.TutorialViewModel
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GoalFormStep(tutorialViewModel: TutorialViewModel, showNextButton: (Boolean) -> Unit) {

    // form values
    val exampleName = stringResource(Res.string.tutorial_goal_example_name)
    var name by remember { mutableStateOf(exampleName) }

    val exampleValue = stringResource(Res.string.tutorial_goal_example_value)
    var value by remember { mutableStateOf(exampleValue) }

    val exampleDate = System.currentTimeMillis() + 604800000
    var date by remember { mutableLongStateOf(exampleDate) }

    // error messages
    val nameBlankError = stringResource(Res.string.name_must_not_be_blank)
    val datePastError = stringResource(Res.string.date_must_be_future)
    val negativePriceError = stringResource(Res.string.price_must_be_positive)
    var errorMessages = remember { mutableSetOf<String>() }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(Res.string.tutorial_create_goal_title),
            fontSize = 25.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.modify(0.8f),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = stringResource(Res.string.tutorial_create_goal_subtitle),
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        MaterialTextField(
            label = stringResource(Res.string.tutorial_goal_name_label),
            icon = Icons.Rounded.NewLabel,
            value = name,
            isError = errorMessages.contains(nameBlankError),
            onValueChange = {
                name = it
                if (name.isBlank()) errorMessages.add(nameBlankError)
                else errorMessages.remove(nameBlankError)
            }
        )

        MaterialDatePicker(
            label = stringResource(Res.string.tutorial_goal_target_date_label),
            icon = Icons.Rounded.EditCalendar,
            value = date,
            dialogAcceptLabel = "Ok",
            dialogDismissLabel = "Cancel",
            isError = errorMessages.contains(datePastError),
            onDateSelected = { it ->
                date = it ?: 0L
                if (date <= System.currentTimeMillis()) errorMessages.add(datePastError)
                else errorMessages.remove(datePastError)
            }
        )

        MaterialTextField(
            type = KeyboardType.Number,
            label = stringResource(Res.string.tutorial_goal_value_label),
            icon = Icons.Rounded.Euro,
            value = value,
            isError = errorMessages.contains(negativePriceError),
            onValueChange = {
                value = it.replace("-", "")
                val num = value.toDoubleOrNull()

                if (null == num || num <= 0.0) errorMessages.add(negativePriceError)
                else errorMessages.remove(negativePriceError)
            }
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
                this.name = name
                this.targetValue = value.toDoubleOrNull() ?: 0.0
                this.targetTimestamp = date
            }
            showNextButton(true)
        }
    }
}

