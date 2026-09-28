package com.godlike.taskit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.PersonalContainer
import com.godlike.taskit.ui.theme.PersonalText
import com.godlike.taskit.ui.theme.Primary
import com.godlike.taskit.ui.theme.Secondary
import com.godlike.taskit.ui.theme.SecondaryText
import com.godlike.taskit.ui.theme.SurfaceContainer
import com.godlike.taskit.ui.theme.SurfaceVariant
import com.godlike.taskit.ui.theme.TextPrimary
import com.godlike.taskit.ui.theme.Urgent

@Composable
fun TaskItem(
    task: Task,
    onDeleteTask: (Task) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(SurfaceContainer)
            .padding(20.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CircleToggleButton(selected = task.isCompleted, onClick = { onCheckedChange(it) })
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = task.title,
                color = TextPrimary,
                fontSize = 24.sp,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            Row(
               horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(color = Secondary)
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                ) {
                    Text(
                        text = "Work",
                        color = SecondaryText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = InterFontFamily
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(color = PersonalContainer)
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                ) {
                    Text(
                        text = "Personal",
                        color = PersonalText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = InterFontFamily
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "delete",
            tint = Urgent,
            modifier = Modifier.clickable{ onDeleteTask(task) }
        )
    }
}

@Composable
fun CircleToggleButton(
    selected: Boolean,
    onClick: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(if (selected) Primary else SurfaceVariant)
            .clickable { onClick(!selected) }
    )
}

@Preview
@Composable
fun PreviewTaskItem() {
    TaskItem(
        task = Task(
            title = "Title",
            description = "Description",
        ),
        onDeleteTask = {},
        onCheckedChange = {},
    )
}