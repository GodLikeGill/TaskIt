package com.godlike.taskit.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.taskItRed
import com.godlike.taskit.ui.theme.white

@Composable
fun TaskItTopAppBar() {
}

@Composable
fun TasksTopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.tasks),
            fontSize = 32.sp,
            fontFamily = InterFontFamily,
            textAlign = TextAlign.Center,
            color = white,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun LoginTopAppBar() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = taskItRed,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = InterFontFamily,
        )
    }
}

@Preview
@Composable
fun PreviewLoginTopAppBar() {
    LoginTopAppBar()
}

@Preview
@Composable
fun PreviewTasksTopAppBar() {
    TasksTopAppBar()
}