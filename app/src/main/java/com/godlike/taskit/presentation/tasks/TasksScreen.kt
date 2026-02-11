package com.godlike.taskit.presentation.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.godlike.taskit.R
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.util.TasksTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = { TasksTopAppBar() },
        floatingActionButton = {
            SmallFloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(
                    imageVector = Icons.Filled.Add, contentDescription = ""
                )
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            TasksContent(viewModel = viewModel)
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = colorResource(R.color.black)
                ) {
                    ModalSheetContent(viewModel = viewModel, onClose = { showBottomSheet = false })
                }
            }
        }
    }
}

@Composable
fun TasksContent(viewModel: TasksViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    LazyColumn {
        items(tasks) { task ->
            TaskItem(
                task,
                onCheckedChange = { isChecked ->
                    viewModel.onCompleteTask(
                        taskId = task.id,
                        isCompleted = isChecked
                    )
                },
                onDeleteTask = {
                    viewModel.onDeleteTask(taskId = task.id)
                }
            )
        }
    }
}

@Composable
fun TaskItem(task: Task, onCheckedChange: (Boolean) -> Unit, onDeleteTask: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange
        )
        Text(
            modifier = Modifier.weight(1f),
            text = task.title,
            color = colorResource(R.color.white_less),
            textAlign = TextAlign.Start,
            textDecoration = if (task.isCompleted)
                TextDecoration.LineThrough
            else
                TextDecoration.None
        )
        IconButton(
            onClick = {
                onDeleteTask()
            },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = colorResource(R.color.white_less)
            )
        ) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
        }
    }
}

@Preview
@Composable
fun PreviewTaskItem() {
    TaskItem(
        task = Task(
            title = "Title",
            description = "Description",
        ),
        onCheckedChange = {},
        onDeleteTask = {}
    )
}

@Preview
@Composable
fun PreviewTasksScreen() {
    TasksScreen()
}