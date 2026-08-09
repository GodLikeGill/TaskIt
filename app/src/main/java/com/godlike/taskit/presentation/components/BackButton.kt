package com.godlike.taskit.presentation.components

import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.godlike.taskit.ui.theme.white

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    onClick: () -> Unit,
    size: Dp = 48.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .border(
                width = 1.dp, color = white.copy(alpha = 0.1f), shape = CircleShape
            )
            .background(
                color = white.copy(alpha = 0.05f), shape = CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Close",
            tint = white,
            modifier = Modifier.size(size * 0.75f)
        )
    }
}

@Preview
@Composable
fun PreviewBackButton() {
    BackButton(onClick = {})
}