package com.godlike.taskit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.godlike.taskit.presentation.auth.AuthScreen
import com.godlike.taskit.presentation.setting.SettingsScreen
import com.godlike.taskit.presentation.tasks.TasksScreen

@Composable
fun TaskItNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = TaskItDestinations.AUTH_ROUTE,
    navActions: TaskItNavigationActions = remember(navController) {
        TaskItNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = TaskItDestinations.AUTH_ROUTE) {
            AuthScreen(onAuthSuccess = { navActions.navigateToTasks() })
        }
        composable(route = TaskItDestinations.TASKS_ROUTE) {
            TasksScreen(onSettingsClick = { navActions.navigateToSettings() })
        }
        composable(route = TaskItDestinations.SETTINGS_ROUTE) {
            SettingsScreen(onLogoutButtonClick = { navActions.navigateToAuth() })
        }
    }
}