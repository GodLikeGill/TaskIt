package com.godlike.taskit.presentation.components

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.godlike.taskit.ui.theme.white

@Composable
fun CloseButton(
    onClose: () -> Unit
) {
    IconButton(
        onClick = onClose,
        modifier = Modifier
            .size(48.dp)
            .border(
                width = 1.dp,
                color = white.copy(alpha = 0.1f),
                shape = CircleShape
            )
            .background(
                color = white.copy(alpha = 0.05f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = white
        )
    }
}