package com.godlike.taskit

import androidx.navigation.NavHostController
import com.godlike.taskit.TaskItDestinations.AUTH_ROUTE
import com.godlike.taskit.TaskItDestinations.SETTINGS_ROUTE
import com.godlike.taskit.TaskItDestinations.TASKS_ROUTE
import com.godlike.taskit.TaskItScreens.AUTH_SCREEN
import com.godlike.taskit.TaskItScreens.SETTINGS_SCREEN
import com.godlike.taskit.TaskItScreens.TASKS_SCREEN

/**
 * Screens used in [TaskItDestinations]
 */
private object TaskItScreens {
    const val AUTH_SCREEN = "auth"
    const val TASKS_SCREEN = "tasks"
    const val SETTINGS_SCREEN = "settings"
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
    const val TASKS_ROUTE = TASKS_SCREEN
    const val SETTINGS_ROUTE = SETTINGS_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class TaskItNavigationActions(private val navController: NavHostController) {
    fun navigateToAuth() {
        navController.navigate(AUTH_ROUTE)
    }

    fun navigateToTasks() {
        navController.navigate(TASKS_ROUTE)
    }

    fun navigateToSettings() {
        navController.navigate(SETTINGS_ROUTE)
    }
}