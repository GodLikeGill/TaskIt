package com.godlike.taskit

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.godlike.taskit.ui.theme.TaskItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskItActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        super.onCreate(savedInstanceState)
        setContent {
            TaskItTheme {
                TaskItNavGraph()
            }
        }
    }
}