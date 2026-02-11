package com.godlike.taskit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.godlike.taskit.ui.theme.TaskItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskItActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskItTheme {
                TaskItNavGraph()
            }
        }
    }
}