package com.godlike.taskit.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R

@Composable
fun TaskItTopAppBar() {

}

@Composable
fun TasksTopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.tasks),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.white_less),
        )
    }
}

@Preview
@Composable
fun PreviewTasksTopAppBar() {
    TasksTopAppBar()
}