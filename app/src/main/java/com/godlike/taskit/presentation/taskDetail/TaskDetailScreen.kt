package com.godlike.taskit.presentation.taskDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onBackButtonClick: () -> Unit
) {
    TaskDetailScreenContent(onBackButtonClick)
}

@Composable
fun TaskDetailScreenContent(
    onBackButtonClick: () -> Unit,
) {
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
                Text(
                    text = "Submit Q3 Android Architecture",
                    color = TextPrimary,
                    fontFamily = Roboto,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
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
                Text(
                    text = "lorem ipsum dolor sit amet consectetur adipiscing elit cillum pariatur sed libero nisi mollit id veniam labore nulla culpa voluptatum mollit occaecat autem est tempore dolores labore occaecat incididunt lorem dolor molestias laborum placeat nobis",
                    color = TextSecondary,
                    fontFamily = Roboto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SurfaceContainer)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(0.4f)
                    .clickable { }
                    .background(color = SurfaceContainerHigh, RoundedCornerShape(10.dp))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Icon",
                    tint = PrimaryLow,
                )
                Text(
                    text = "Delete Task",
                    color = PrimaryLow,
                    fontFamily = Roboto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                modifier = Modifier
                    .weight(0.6f)
                    .clickable { }
                    .background(color = Primary, RoundedCornerShape(10.dp))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircleOutline,
                    contentDescription = "Delete Icon",
                    tint = PrimaryDark,
                )
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
    TaskDetailScreenContent(onBackButtonClick = {})
}