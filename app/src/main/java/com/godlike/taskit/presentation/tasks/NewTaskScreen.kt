package com.godlike.taskit.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.CheckCircleOutline
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
import com.godlike.taskit.ui.theme.Roboto
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.ui.theme.SurfaceContainer
import com.godlike.taskit.ui.theme.SurfaceContainerHigh
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
                    .clip(RoundedCornerShape(20.dp))
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
                    onSelect = { selectedCategory = if(selectedCategory == "Personal") null else "Personal"}
                )
                CategoryComponent(
                    categoryText = "Work",
                    categoryColor = WorkText,
                    isSelected = selectedCategory == "Work",
                    onSelect = { selectedCategory = if(selectedCategory == "Work") null else "Work"}
                )
                CategoryComponent(
                    categoryText = "+ New Tag",
                    categoryColor = TextTertiary,
                    isSelected = false,
                    onSelect = { /*Do Something*/ }
                )
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
            .clickable{ onSelect() }
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

@Preview
@Composable
fun PreviewNewTaskScreen() {
    NewTaskScreen(onCreateTask = {}, onBack = {})
}