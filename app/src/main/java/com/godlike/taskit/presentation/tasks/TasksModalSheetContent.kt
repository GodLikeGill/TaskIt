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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.darkGray
import com.godlike.taskit.ui.theme.lightGray
import com.godlike.taskit.ui.theme.taskRed
import com.godlike.taskit.ui.theme.white

@Composable
fun ModalSheetContent(
    onClose: (Task) -> Unit,
) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.add_task),
            color = white,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )
        BasicTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 24.sp,
                color = lightGray,
                fontFamily = InterFontFamily
            ),
            decorationBox = { innerTextField ->
                if (taskTitle.isEmpty()) Text(
                    text = stringResource(R.string.add_task_title),
                    fontFamily = InterFontFamily,
                    color = darkGray,
                    fontSize = 20.sp
                )
                innerTextField()
            })
        BasicTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = lightGray,
                fontFamily = InterFontFamily
            ),
            decorationBox = { innerTextField ->
                if (taskDescription.isEmpty()) Text(
                    text = stringResource(R.string.description),
                    fontFamily = InterFontFamily,
                    color = darkGray,
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
                    if (taskTitle.isNotEmpty() && taskDescription.isNotEmpty())
                        onClose(Task(title = taskTitle, description = taskDescription))
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = taskRed,
                    contentColor = white,
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
    ModalSheetContent(onClose = {})
}