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

object TaskItDestinationArgs {
    const val USER = "currentUser"
}

object TaskItDestinations {
    const val AUTH_ROUTE = AUTH_SCREEN
    const val TASKS_ROUTE = "$TASKS_SCREEN/{$USER}"
}

class TaskItNavigationActions(private val navController: NavHostController) {
    fun navigateToAuth() {
        navController.navigate(AUTH_SCREEN)
    }

    fun navigateToTasks(user: User) {
        navController.navigate("$TASKS_SCREEN/$user")
    }
}