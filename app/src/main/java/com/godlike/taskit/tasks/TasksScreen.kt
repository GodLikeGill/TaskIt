package com.godlike.taskit.tasks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.godlike.taskit.R
import com.godlike.taskit.data.Task
import com.godlike.taskit.util.TasksTopAppBar

@Composable
fun TasksScreen(viewModel: TasksViewModel = hiltViewModel()) {
    Scaffold(
        containerColor = colorResource(id = R.color.background),
        topBar = { TasksTopAppBar() },
        floatingActionButton = {
            SmallFloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = ""
                )
            }
        }
    ) { paddingValues ->
        TasksContent(
            modifier = Modifier.padding(paddingValues),
            viewModel = viewModel,
        )
    }
}

@Composable
fun TasksContent(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel,
) {
    val tasks by viewModel.tasks.collectAsState()

    LazyColumn {
        items(tasks) { task ->
            TaskItem(task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = {},
        )
        Text(
            text = task.title,
            color = colorResource(R.color.white_less),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewTaskItem() {
    TaskItem(
        task = Task(
            title = "Title",
            description = "Description",
        )
    )
}

@Preview
@Composable
fun PreviewTasksScreen() {
    TasksScreen()
}