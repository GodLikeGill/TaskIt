package com.godlike.taskit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.godlike.taskit.presentation.login.LoginScreen
import com.godlike.taskit.presentation.tasks.TasksScreen

@Composable
fun TaskItNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination : String = "tasks",
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(startDestination) { LoginScreen() }
    }
}