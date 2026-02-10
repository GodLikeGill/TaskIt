package com.godlike.taskit.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedIconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godlike.taskit.R
import com.godlike.taskit.data.Task
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
                    viewModel.completeTask(
                        taskId = task.id,
                        isCompleted = isChecked
                    )
                },
                onDeleteTask = {
                    viewModel.deleteTask(taskId = task.id)
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

@Composable
fun ModalSheetContent(
    viewModel: TasksViewModel,
    onClose: () -> Unit,
) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.add_task),
            color = colorResource(R.color.white_less),
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )
        BasicTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 24.sp, color = colorResource(R.color.white_less)),
            decorationBox = { innerTextField ->
                if (taskTitle.isEmpty()) Text(
                    text = stringResource(R.string.add_task_title),
                    color = Color.Gray,
                    fontSize = 20.sp
                )
                innerTextField()
            })
        BasicTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 20.sp, color = colorResource(R.color.white_less)),
            decorationBox = { innerTextField ->
                if (taskDescription.isEmpty()) Text(
                    text = stringResource(R.string.description),
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                innerTextField()
            })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            FilledIconButton(
                onClick = {
                    onClose()
                    viewModel.addTask(Task(title = taskTitle, description = taskDescription))
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Black,
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "",
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewModalSheetContent() {
    ModalSheetContent(viewModel = viewModel(), onClose = {})
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