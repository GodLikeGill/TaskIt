package com.godlike.taskit.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.godlike.taskit.R
import com.godlike.taskit.data.Task
import com.godlike.taskit.util.TasksTopAppBar

@Composable
fun TasksScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            TasksTopAppBar()
        }
    ) { paddingValues ->
        TasksContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composabl
fun TasksContent(
    modifier: Modifier = Modifier
) {
    LazyColumn {
        /*items(tasks) { task ->
            TaskItem(task = task)
        }*/
    }
}

@Composable
fun TaskItem(task: Task) {
    Row (verticalAlignment = Alignment.CenterVertically) {
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