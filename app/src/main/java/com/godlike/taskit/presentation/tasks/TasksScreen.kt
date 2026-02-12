package com.godlike.taskit.presentation.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.godlike.taskit.R
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.presentation.components.TaskItem
import com.godlike.taskit.ui.theme.black
import com.godlike.taskit.ui.theme.taskRed
import com.godlike.taskit.ui.theme.white
import com.godlike.taskit.util.TasksTopAppBar

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    TasksScreenContent(
        tasks,
        onAddTask = { viewModel.onAddTask(it) },
        onDeleteTasks = { viewModel.onDeleteTask(it) },
        onCheckedChange = { taskId, isCompleted -> viewModel.onCompleteTask(taskId, isCompleted) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreenContent(
    tasks: List<Task>,
    onAddTask: (Task) -> Unit,
    onDeleteTasks: (String) -> Unit,
    onCheckedChange: (String, Boolean) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = { TasksTopAppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showBottomSheet = true },
                shape = CircleShape,
                containerColor = taskRed,
                contentColor = white
            ) {
                Icon(
                    imageVector = Icons.Filled.Add, contentDescription = ""
                )
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {},
                        onDeleteTask = { onDeleteTasks(task.id) },
                        onCheckedChange = { isChecked -> onCheckedChange(task.id, isChecked) },
                    )
                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = black
                ) {
                    ModalSheetContent(onClose = { task ->
                        onAddTask(task)
                        showBottomSheet = false
                    })
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTasksScreenContent() {
    val fakeTasks = listOf(
        Task(
            id = "1",
            title = "Buy groceries",
            description = "Milk, Eggs, Bread",
            isCompleted = false
        ),
        Task(
            id = "2",
            title = "Workout",
            description = "Chest day",
            isCompleted = true
        )
    )

    TasksScreenContent(
        tasks = fakeTasks,
        onAddTask = {},
        onDeleteTasks = {},
        onCheckedChange = { _, _ -> }
    )
}