package com.godlike.taskit.nav

import androidx.navigation.NavHostController
import com.godlike.taskit.nav.TaskItDestinations.AUTH_ROUTE
import com.godlike.taskit.nav.TaskItDestinations.NEW_TASK_ROUTE
import com.godlike.taskit.nav.TaskItDestinations.SEARCH_ROUTE
import com.godlike.taskit.nav.TaskItDestinations.SETTINGS_ROUTE
import com.godlike.taskit.nav.TaskItDestinations.SIGN_IN_ROUTE
import com.godlike.taskit.nav.TaskItDestinations.SIGN_UP_ROUTE
import com.godlike.taskit.nav.TaskItDestinations.TASKS_ROUTE
import com.godlike.taskit.nav.TaskItDestinations.UPCOMING_ROUTE
import com.godlike.taskit.nav.TaskItScreens.AUTH_SCREEN
import com.godlike.taskit.nav.TaskItScreens.NEW_TASK_SCREEN
import com.godlike.taskit.nav.TaskItScreens.SEARCH_SCREEN
import com.godlike.taskit.nav.TaskItScreens.SETTINGS_SCREEN
import com.godlike.taskit.nav.TaskItScreens.SIGN_IN_SCREEN
import com.godlike.taskit.nav.TaskItScreens.SIGN_UP_SCREEN
import com.godlike.taskit.nav.TaskItScreens.TASKS_SCREEN
import com.godlike.taskit.nav.TaskItScreens.UPCOMING_SCREEN

/**
 * Screens used in [TaskItDestinations]
 */
private object TaskItScreens {
    const val AUTH_SCREEN = "auth"
    const val TASKS_SCREEN = "tasks"
    const val UPCOMING_SCREEN = "upcoming"
    const val SEARCH_SCREEN = "search"
    const val SETTINGS_SCREEN = "settings"
    const val NEW_TASK_SCREEN = "newTask"
    const val SIGN_IN_SCREEN = "signIn"
    const val SIGN_UP_SCREEN = "signUp"
}


/**
 * Destinations used in [TaskItNavGraph]
 */
object TaskItDestinations {
    const val AUTH_ROUTE = AUTH_SCREEN
    const val TASKS_ROUTE = TASKS_SCREEN
    const val UPCOMING_ROUTE = UPCOMING_SCREEN
    const val NEW_TASK_ROUTE = NEW_TASK_SCREEN
    const val SEARCH_ROUTE = SEARCH_SCREEN
    const val SETTINGS_ROUTE = SETTINGS_SCREEN
    const val SIGN_IN_ROUTE = SIGN_IN_SCREEN
    const val SIGN_UP_ROUTE = SIGN_UP_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class TaskItNavigationActions(private val navController: NavHostController) {
    fun navigateToAuth() {
        navController.navigate(AUTH_ROUTE)
    }
    fun navigateToSearch() {
        navController.navigate(SEARCH_ROUTE)
    }
    fun navigateToTasks() {
        navController.navigate(TASKS_ROUTE)
    }
    fun navigateToUpcoming() {
        navController.navigate(UPCOMING_ROUTE)
    }
    fun navigateToNewTask() {
        navController.navigate(NEW_TASK_ROUTE)
    }
    fun navigateToSettings() {
        navController.navigate(SETTINGS_ROUTE)
    }
    fun navigateToSignIn() {
        navController.navigate(SIGN_IN_ROUTE)
    }
    fun navigateToSignUp() {
        navController.navigate(SIGN_UP_ROUTE)
    }
}