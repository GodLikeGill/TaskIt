package com.godlike.taskit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.godlike.taskit.presentation.auth.AuthScreen
import com.godlike.taskit.presentation.setting.SettingsScreen
import com.godlike.taskit.presentation.tasks.TasksScreen
import com.godlike.taskit.ui.theme.Surface

@Composable
fun TaskItNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = TaskItDestinations.TASKS_ROUTE,
    navActions: TaskItNavigationActions = remember(navController) {
        TaskItNavigationActions(navController)
    }
) {
    Scaffold(
        containerColor = Surface
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = TaskItDestinations.AUTH_ROUTE) {
                AuthScreen(onAuthSuccess = { navActions.navigateToTasks() })
            }
            composable(route = TaskItDestinations.TASKS_ROUTE) {
                TasksScreen()
            }
            composable(route = TaskItDestinations.SETTINGS_ROUTE) {
                SettingsScreen(onLogoutButtonClick = { navActions.navigateToAuth() })
            }
        }
    }
}