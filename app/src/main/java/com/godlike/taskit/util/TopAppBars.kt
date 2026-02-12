package com.godlike.taskit.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.white

@Composable
fun TaskItTopAppBar() {}

@Composable
fun TasksTopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = stringResource(id = R.string.tasks),
            fontSize = 32.sp,
            fontFamily = InterFontFamily,
            textAlign = TextAlign.Center,
            color = white,
        )
    }
}

@Preview
@Composable
fun PreviewTasksTopAppBar() {
    TasksTopAppBar()
}