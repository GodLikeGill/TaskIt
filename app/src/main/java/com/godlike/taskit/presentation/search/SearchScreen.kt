package com.godlike.taskit.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godlike.taskit.ui.theme.Primary
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.util.SearchTopAppBar

@Composable
fun SearchScreen() {
    SearchScreenContent()
}

@Composable
fun SearchScreenContent() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Surface)
            .padding(10.dp),
    ) {
        Column {
            SearchTopAppBar()
        }
        Text(
            text = "Coming Soon ...",
            color = Primary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewSearchScreenContent() {
    SearchScreenContent()
}