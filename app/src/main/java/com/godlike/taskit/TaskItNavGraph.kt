package com.godlike.taskit

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.godlike.taskit.presentation.search.SearchScreen
import com.godlike.taskit.presentation.setting.SettingsScreen
import com.godlike.taskit.presentation.tasks.NewTaskScreen
import com.godlike.taskit.presentation.tasks.TasksScreen
import com.godlike.taskit.presentation.tasks.TasksViewModel
import com.godlike.taskit.presentation.upcoming.UpcomingScreen
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.util.BottomNavItem
import com.godlike.taskit.util.TaskItBottomNav

@Composable
fun TaskItNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = TaskItDestinations.TASKS_ROUTE,
    navActions: TaskItNavigationActions = remember(navController) {
        TaskItNavigationActions(navController)
    }
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val items = listOf(
        BottomNavItem(TaskItDestinations.TASKS_ROUTE, "Tasks", Icons.Outlined.CheckCircleOutline),
        BottomNavItem(TaskItDestinations.UPCOMING_ROUTE, "Upcoming", Icons.Outlined.CalendarMonth),
        BottomNavItem(TaskItDestinations.SEARCH_ROUTE, "Search", Icons.Outlined.Search),
        BottomNavItem(TaskItDestinations.SETTINGS_ROUTE, "Settings", Icons.Outlined.Settings),
    )
    Scaffold(
        containerColor = Surface,
        bottomBar = {
            if (currentDestination?.route != TaskItDestinations.NEW_TASK_ROUTE) {
                TaskItBottomNav(
                    items = items,
                    selectedRoute = items.firstOrNull { item -> currentDestination?.hierarchy?.any { it.route == item.route } == true }?.route,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(TaskItDestinations.TASKS_ROUTE) { TasksScreen(onNewTaskButtonClick = { navActions.navigateToNewTask() }) }
            composable(TaskItDestinations.UPCOMING_ROUTE) { UpcomingScreen() }
            composable(TaskItDestinations.SEARCH_ROUTE) { SearchScreen() }
            composable(TaskItDestinations.SETTINGS_ROUTE) { SettingsScreen(onLogoutButtonClick = { navActions.navigateToAuth() }) }
            composable(TaskItDestinations.NEW_TASK_ROUTE) {
                val viewModel: TasksViewModel = hiltViewModel()
                NewTaskScreen(
                    onCreateTask = { task ->
                        viewModel.onAddTask(task)
                        navController.popBackStack()
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}