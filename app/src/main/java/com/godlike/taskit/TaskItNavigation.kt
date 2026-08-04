package com.godlike.taskit

import androidx.navigation.NavHostController
import com.godlike.taskit.TaskItDestinationArgs.USER
import com.godlike.taskit.TaskItScreens.AUTH_SCREEN
import com.godlike.taskit.TaskItScreens.TASKS_SCREEN
import com.godlike.taskit.domain.model.User

/**
 * Screens used in [TaskItDestinations]
 */
private object TaskItScreens {
    const val AUTH_SCREEN = "auth"
    const val TASKS_SCREEN = "tasks"
}

/**
 * Arguments used in [TaskItDestinations]
 */
object TaskItDestinationArgs {
    const val USER = "user"
}

/**
 * Destinations used in [TaskItNavGraph]
 */
object TaskItDestinations {
    const val AUTH_ROUTE = AUTH_SCREEN
        //const val TASKS_ROUTE = "$TASKS_SCREEN/$USER"
    const val TASKS_ROUTE = TaskItScreens.TASKS_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class TaskItNavigationActions(private val navController: NavHostController) {
    fun navigateToAuth() {
        navController.navigate(AUTH_SCREEN)
    }

    fun navigateToTasks() {
        navController.navigate(TaskItDestinations.TASKS_ROUTE)
    }
}