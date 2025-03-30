package org.akrck02.countless.ui.view.tutorial.step

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import countless.composeapp.generated.resources.Res
import countless.composeapp.generated.resources.name_must_not_be_blank
import countless.composeapp.generated.resources.tutorial_account_example_name
import countless.composeapp.generated.resources.tutorial_create_account_subtitle
import countless.composeapp.generated.resources.tutorial_create_account_title
import countless.composeapp.generated.resources.tutorial_goal_name_label
import org.akrck02.countless.ui.component.MaterialTextField
import org.akrck02.countless.ui.extension.modify
import org.akrck02.countless.viewmodel.TutorialViewModel
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CreateAccountStep(tutorialViewModel: TutorialViewModel, showNextButton: (Boolean) -> Unit) {

    // values
    val exampleName = stringResource(Res.string.tutorial_account_example_name)

    // error messages
    var errorMessage: String? by remember { mutableStateOf(null) }
    var name by remember { mutableStateOf(exampleName) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(Res.string.tutorial_create_account_title),
            fontSize = 25.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.modify(0.8f),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = stringResource(Res.string.tutorial_create_account_subtitle),
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.modify(0.5f),
            modifier = Modifier.padding(bottom = 40.dp)
        )


        val blankErrorMessage = stringResource(Res.string.name_must_not_be_blank)
        MaterialTextField(
            label = stringResource(Res.string.tutorial_goal_name_label),
            icon = Icons.Rounded.AccountBalance,
            value = name,
            isError = null != errorMessage,
            onValueChange = {
                name = it
                errorMessage = blankErrorMessage.takeIf { name.isBlank() }
            }
        )

        errorMessage?.also {
            Text(
                text = it,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 40.dp)
            )
            showNextButton(false)
        } ?: run {
            tutorialViewModel.account.name = name
            showNextButton(true)
        }

    }
}