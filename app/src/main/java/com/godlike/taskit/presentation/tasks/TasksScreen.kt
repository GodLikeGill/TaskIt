package com.godlike.taskit.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.presentation.components.TaskItem
import com.godlike.taskit.ui.theme.Primary
import com.godlike.taskit.ui.theme.PrimaryDark
import com.godlike.taskit.ui.theme.PrimaryLow
import com.godlike.taskit.ui.theme.PrimaryPressed
import com.godlike.taskit.ui.theme.Roboto
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.ui.theme.SurfaceContainer
import com.godlike.taskit.ui.theme.SurfaceContainerHigh
import com.godlike.taskit.ui.theme.TextPrimary
import com.godlike.taskit.ui.theme.TextSecondary
import com.godlike.taskit.util.TasksTopAppBar
import java.util.Calendar
import java.util.Locale

data class DateItem(
    val dayNumber: Int,
    val dayName: String
)

fun getDateList(): List<DateItem> {
    val calendar = Calendar.getInstance()
    return (0 until 30).map {
        calendar.add(Calendar.DAY_OF_MONTH, if (it == 0) 0 else 1)
        DateItem(
            dayNumber = calendar.get(Calendar.DAY_OF_MONTH),
            dayName = calendar.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.SHORT,
                Locale.getDefault()
            ) ?: ""
        )
    }
}

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    TasksScreenContent(
        tasks,
        onAddTask = { viewModel.onAddTask(it) },
        onDeleteTask = { viewModel.onDeleteTask(it) },
        onCheckedChange = { taskId, isCompleted -> viewModel.onCompleteTask(taskId, isCompleted) })
}

@Composable
fun TasksScreenContent(
    tasks: List<Task>,
    onAddTask: (Task) -> Unit,
    onDeleteTask: (String) -> Unit,
    onCheckedChange: (String, Boolean) -> Unit
) {
    val dates = remember { getDateList() }
    var selectedIndex by remember { mutableIntStateOf(0) }
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Surface)
            .padding(10.dp),
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TasksTopAppBar()
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                itemsIndexed(dates) { index, date ->
                    val isSelected = index == selectedIndex
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isSelected) PrimaryPressed else SurfaceContainer,
                            )
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = date.dayName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSelected) PrimaryDark else PrimaryLow,
                        )
                        Text(
                            text = date.dayNumber.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) PrimaryDark else TextSecondary
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                FilterButtons("All (8)") {}
                FilterButtons("Work (3)") {}
                FilterButtons("Personal (4)") {}
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onDeleteTask = { onDeleteTask(task.id) },
                        onCheckedChange = { isChecked -> onCheckedChange(task.id, isChecked) },
                    )
                }
            }
        }
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(50),
                    clip = false,
                    ambientColor = Primary,
                    spotColor = Primary,
                ),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = TextPrimary
            ),
            contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add",
                    tint = Surface,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "New Task",
                    color = Surface,
                    fontSize = 15.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun FilterButtons(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = SurfaceContainerHigh)
            .padding(vertical = 8.dp, horizontal = 20.dp)
    ) {
        Text(
            text = text,
            color = PrimaryLow
        )
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
        ), Task(
            id = "2", title = "Workout", description = "Chest day", isCompleted = true
        )
    )

    TasksScreenContent(
        tasks = fakeTasks,
        onAddTask = {},
        onDeleteTask = {},
        onCheckedChange = { _, _ -> }
    )
}