package com.godlike.taskit.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.PersonalText
import com.godlike.taskit.ui.theme.Primary
import com.godlike.taskit.ui.theme.PrimaryDark
import com.godlike.taskit.ui.theme.PrimaryLow
import com.godlike.taskit.ui.theme.Roboto
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.ui.theme.SurfaceContainer
import com.godlike.taskit.ui.theme.SurfaceContainerHigh
import com.godlike.taskit.ui.theme.SurfaceVariant
import com.godlike.taskit.ui.theme.TextPrimary
import com.godlike.taskit.ui.theme.TextTertiary
import com.godlike.taskit.ui.theme.WorkText
import com.godlike.taskit.ui.theme.darkGray
import com.godlike.taskit.ui.theme.lightGray
import com.godlike.taskit.util.NewTaskTopAppBar

@Composable
fun NewTaskScreen(
    onBack: () -> Unit,
    onCreateTask: (Task) -> Unit,
    taskTitle: String = "",
    taskDescription: String = "",
) {

    var taskTitle by remember { mutableStateOf(taskTitle) }
    var taskDescription by remember { mutableStateOf(taskDescription) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Surface)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        NewTaskTopAppBar(onBackButtonPress = { onBack() })
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "TASK DETAIL",
                color = TextTertiary,
                fontFamily = Roboto
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(SurfaceContainer)
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BasicTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontSize = 24.sp, color = lightGray, fontFamily = InterFontFamily
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
                        fontSize = 20.sp, color = lightGray, fontFamily = InterFontFamily
                    ),
                    decorationBox = { innerTextField ->
                        if (taskDescription.isEmpty()) Text(
                            text = stringResource(R.string.Description),
                            fontFamily = InterFontFamily,
                            color = darkGray,
                            fontSize = 16.sp
                        )
                        innerTextField()
                    }
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "DUE DATE & TIME",
                color = TextTertiary,
                fontFamily = Roboto
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DateComponent(
                    text = "Today",
                    isSelected = selectedDate == "Today",
                    icon = Icons.Outlined.CalendarToday,
                    onClick = { selectedDate = if (selectedDate == "Today") null else "Today" },
                )
                DateComponent(
                    text = "Tomorrow",
                    isSelected = selectedDate == "Tomorrow",
                    icon = Icons.Outlined.EditCalendar,
                    onClick = {
                        selectedDate = if (selectedDate == "Tomorrow") null else "Tomorrow"
                    },
                )
                DateComponent(
                    text = "Pick Date",
                    isSelected = selectedDate == "Pick Date",
                    icon = Icons.Outlined.CalendarMonth,
                    onClick = {
                        selectedDate = if (selectedDate == "Pick Date") null else "Pick Date"
                    },
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = SurfaceContainer,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccessTime,
                        contentDescription = "Time icon",
                        tint = PrimaryLow,
                        modifier = Modifier
                            .background(
                                color = SurfaceContainerHigh,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(5.dp)
                    )
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Target Time",
                            color = TextTertiary,
                            fontFamily = Roboto,
                            fontSize = 10.sp
                        )
                        Text(
                            text = "10:30 AM",
                            color = TextPrimary,
                            fontFamily = Roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = SurfaceVariant,
                                shape = CircleShape
                            )
                            .padding(vertical = 3.dp, horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Edit",
                            color = TextPrimary,
                            fontFamily = Roboto,
                        )
                    }
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "CATEGORY",
                color = TextTertiary,
                fontFamily = Roboto
            )
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                CategoryComponent(
                    categoryText = "Personal",
                    categoryColor = PersonalText,
                    isSelected = selectedCategory == "Personal",
                    onSelect = {
                        selectedCategory = if (selectedCategory == "Personal") null else "Personal"
                    }
                )
                CategoryComponent(
                    categoryText = "Work",
                    categoryColor = WorkText,
                    isSelected = selectedCategory == "Work",
                    onSelect = {
                        selectedCategory = if (selectedCategory == "Work") null else "Work"
                    }
                )
                CategoryComponent(
                    categoryText = "New Tag",
                    categoryColor = TextTertiary,
                    isSelected = false,
                    onSelect = { /*Do Something*/ }
                )
            }
        }

        var selectedPriorityLevel by remember { mutableStateOf("Low") }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "PRIORITY LEVEL",
                color = TextTertiary,
                fontFamily = Roboto
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = SurfaceContainer,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(3.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = if (selectedPriorityLevel == "Low") Primary else Color.Transparent,
                            shape = RoundedCornerShape(7.dp)
                        )
                        .padding(vertical = 6.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "● Low",
                        color = if (selectedPriorityLevel == "Low") PrimaryDark else TextTertiary,
                    )
                }
                Row(
                    modifier = Modifier
                        .background(
                            color = if (selectedPriorityLevel == "Normal") Primary else Color.Transparent,
                            shape = RoundedCornerShape(7.dp)
                        )
                        .padding(vertical = 6.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "● Normal",
                        color = if (selectedPriorityLevel == "Normal") PrimaryDark else TextPrimary,
                    )
                }
                Row(
                    modifier = Modifier
                        .background(
                            color = if (selectedPriorityLevel == "High") Primary else Color.Transparent,
                            shape = RoundedCornerShape(7.dp)
                        )
                        .padding(vertical = 6.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "● High",
                        color = if (selectedPriorityLevel == "High") PrimaryDark else PrimaryLow,
                    )
                }
            }
        }
        Spacer(Modifier.weight(1f))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        onCreateTask(
                            Task(
                                title = taskTitle, description = taskDescription
                            )
                        )
                    }
                    .background(color = Primary)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp, alignment = Alignment.CenterHorizontally
                ), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircleOutline,
                    contentDescription = "",
                    tint = PrimaryDark,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.create_task),
                    color = PrimaryDark,
                    fontSize = 15.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = stringResource(R.string.discard_draft),
                fontSize = 12.sp,
                color = TextTertiary,
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onBack() }
            )
        }
    }
}

@Composable
fun CategoryComponent(
    categoryText: String,
    categoryColor: Color,
    isSelected: Boolean = false,
    onSelect: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(
                color = SurfaceContainerHigh,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onSelect() }
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "●",
            color = categoryColor
        )
        Text(
            text = categoryText,
            color = TextPrimary
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Check mark",
                tint = categoryColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun DateComponent(
    text: String,
    icon: ImageVector,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(
                color = if (isSelected) Primary else SurfaceContainer,
                shape = CircleShape
            )
            .clickable { onClick() }
            .padding(horizontal = 25.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        if (isSelected) {
            Icon(
                imageVector = icon,
                contentDescription = "Calender today icon",
                tint = PrimaryDark,
                modifier = Modifier.size(14.dp)
            )
        }
        Text(
            text = text,
            fontSize = 12.sp,
            color = if (isSelected) PrimaryDark else TextPrimary,
            fontFamily = Roboto,
        )
    }
}

@Preview
@Composable
fun PreviewNewTaskScreen() {
    NewTaskScreen(onCreateTask = {}, onBack = {})
}