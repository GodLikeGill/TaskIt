package com.godlike.taskit.presentation.taskDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.util.TaskDetailTopAppBar

@Composable
fun TaskDetailScreen(
    onBackButtonClick: () -> Unit
) {
    TaskDetailScreenContent(onBackButtonClick)
}

@Composable
fun TaskDetailScreenContent(onBackButtonClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Surface)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TaskDetailTopAppBar(onBackButtonClick)

    }
}

@Preview
@Composable
fun PreviewTaskDetailScreenContent() {
    TaskDetailScreenContent(onBackButtonClick = {})
}