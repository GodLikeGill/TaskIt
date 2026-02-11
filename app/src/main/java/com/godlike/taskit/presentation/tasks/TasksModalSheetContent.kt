package com.godlike.taskit.presentation.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godlike.taskit.R
import com.godlike.taskit.domain.model.Task

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
                    viewModel.onAddTask(Task(title = taskTitle, description = taskDescription))
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