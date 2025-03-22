package org.akrck02.countless.ui.component.router

import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController


const val DEFAULT_ANIM_SPEED = 1000


fun NavGraphBuilder.uploadRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, String>
) {
//    composable<UploadRoute>(
//        enterTransition = {
//            slideIntoContainer(
//                AnimatedContentTransitionScope.SlideDirection.Down,
//                animationSpec = tween(DEFAULT_ANIM_SPEED)
//            )
//        },
//        exitTransition = {
//            slideOutOfContainer(
//                AnimatedContentTransitionScope.SlideDirection.Up,
//                animationSpec = tween(DEFAULT_ANIM_SPEED)
//            )
//        }
//    ) {
//        UploadView(navController, gallery)
//    }
}