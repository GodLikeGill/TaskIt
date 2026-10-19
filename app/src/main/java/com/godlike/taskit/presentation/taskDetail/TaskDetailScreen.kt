package com.godlike.taskit.presentation.taskDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.ui.theme.Primary
import com.godlike.taskit.ui.theme.PrimaryDark
import com.godlike.taskit.ui.theme.PrimaryLow
import com.godlike.taskit.ui.theme.Roboto
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.ui.theme.SurfaceContainer
import com.godlike.taskit.ui.theme.SurfaceContainerHigh
import com.godlike.taskit.ui.theme.TextPrimary
import com.godlike.taskit.ui.theme.TextSecondary
import com.godlike.taskit.util.TaskDetailTopAppBar

@Composable
fun TaskDetailScreen(
    onBackButtonClick: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val task by viewModel.task.collectAsStateWithLifecycle()

    TaskDetailScreenContent(
        task = task,
        onBackButtonClick = onBackButtonClick,
        onDeleteTask = { /*viewModel.onDeleteTask(it)*/ },
        onCompleteTask = { _,_ ->/*taskId, isCompleted -> viewModel.onCompleteTask(taskId, isCompleted)*/ }
    )
}

@Composable
fun TaskDetailScreenContent(
    task: Task?,
    onBackButtonClick: () -> Unit,
    onDeleteTask: (String) -> Unit,
    onCompleteTask: (String, Boolean) -> Unit,
) {
    var title: String = task?.title ?: "Submit Q3 Android Architecture"
    var description: String = task?.description ?: "Task1" //lorem ipsum dolor sit amet consectetur adipiscing elit cillum pariatur sed libero nisi mollit id veniam labore nulla culpa voluptatum mollit occaecat autem est tempore dolores labore occaecat incididunt lorem dolor molestias laborum placeat nobis"

    Column {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Surface)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TaskDetailTopAppBar(onBackButtonClick)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = SurfaceContainer, shape = RoundedCornerShape(16.dp)
                    )
                    .padding(15.dp)
            ) {
                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    textStyle = TextStyle(
                        color = TextPrimary,
                        fontSize = 24.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = SurfaceContainer, shape = RoundedCornerShape(16.dp)
                    )
                    .padding(15.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Details",
                    color = PrimaryLow,
                    fontFamily = Roboto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    textStyle = TextStyle(
                        color = TextSecondary,
                        fontSize = 16.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SurfaceContainer)
                .padding(12.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { }
                    .background(color = SurfaceContainerHigh, RoundedCornerShape(10.dp))
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Icon",
                    tint = Primary,
                )
            }
            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .clickable { }
                    .background(color = SurfaceContainerHigh, RoundedCornerShape(10.dp))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Save",
                    color = Primary,
                    fontFamily = Roboto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { }
                    .background(color = Primary, RoundedCornerShape(10.dp))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Complete Task",
                    color = PrimaryDark,
                    fontFamily = Roboto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTaskDetailScreenContent() {
    TaskDetailScreenContent(
        task = null,
        onBackButtonClick = {},
        onDeleteTask = {},
        onCompleteTask = { _, _ -> },
    )
}