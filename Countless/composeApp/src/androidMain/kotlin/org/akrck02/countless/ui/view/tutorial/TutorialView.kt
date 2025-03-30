package org.akrck02.countless.ui.view.tutorial

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.akrck02.countless.data.model.option.Direction
import org.akrck02.countless.ui.component.StepsBar
import org.akrck02.countless.ui.navigation.Route
import org.akrck02.countless.ui.navigation.navigateSecurely
import org.akrck02.countless.ui.navigation.show
import org.akrck02.countless.ui.view.tutorial.step.CreateAccountStep
import org.akrck02.countless.ui.view.tutorial.step.GoalFormStep
import org.akrck02.countless.ui.view.tutorial.step.GoalLimitsStep
import org.akrck02.countless.ui.view.tutorial.step.PreparingAppStep
import org.akrck02.countless.ui.view.tutorial.step.WelcomeStep
import org.akrck02.countless.viewmodel.AppViewModel
import org.akrck02.countless.viewmodel.TutorialViewModel
import org.koin.androidx.compose.koinViewModel

@Serializable
open class Step : Route()

@Serializable
object WelcomeStepRoute : Step()

@Serializable
object CreateAccountStep : Step()

@Serializable
object GoalFormStep : Step()

@Serializable
object GoalLimitsStep : Step()

@Serializable
object PreparingAppStep : Step()

var steps = listOf<Route>(WelcomeStepRoute, CreateAccountStep, GoalFormStep, GoalLimitsStep, PreparingAppStep)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TutorialView(appViewModel: AppViewModel, tutorialViewModel: TutorialViewModel = koinViewModel(), onFinish: () -> Unit) {


    val navController = rememberNavController()
    var step by remember { mutableIntStateOf(0) }
    var showNextButton by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            Column(modifier = Modifier.padding(bottom = 20.dp)) { StepsBar(selected = step, maxSteps = steps.size) }
        },
        floatingActionButton = {
            if (showNextButton && steps.last() != steps[step]) {
                IconButton(
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.surfaceDim,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.size(50.dp),
                    onClick = {
                        step = navController.navigateToNextTutorialStep(step)
                        Log.d("Countless-navigation", "$tutorialViewModel")
                    }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Rounded.NavigateNext, contentDescription = "next")
                }
            }

        },
        floatingActionButtonPosition = FabPosition.Center
    ) {

        NavHost(
            navController = navController,
            startDestination = WelcomeStepRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {
            show<WelcomeStepRoute>(navController = navController, enterDirection = Direction.LEFT, exitDirection = Direction.LEFT) {
                WelcomeStep()
            }
            show<CreateAccountStep>(navController = navController, enterDirection = Direction.LEFT, exitDirection = Direction.LEFT) {
                CreateAccountStep(tutorialViewModel) { showNextButton = it }
            }
            show<GoalFormStep>(navController = navController, enterDirection = Direction.LEFT, exitDirection = Direction.LEFT) {
                GoalFormStep(tutorialViewModel) { showNextButton = it }
            }
            show<GoalLimitsStep>(navController = navController, enterDirection = Direction.LEFT, exitDirection = Direction.LEFT) {
                GoalLimitsStep(tutorialViewModel) { showNextButton = it }
            }

            show<PreparingAppStep>(navController = navController, enterDirection = Direction.LEFT, exitDirection = Direction.LEFT) {
                PreparingAppStep(tutorialViewModel) { onFinish() }
            }
        }
    }
}

private fun NavHostController.navigateToNextTutorialStep(step: Int): Int {


    if (steps.size > step) {
        this.navigateSecurely(steps[step + 1])
        return step + 1
    }

    return step + 1
}