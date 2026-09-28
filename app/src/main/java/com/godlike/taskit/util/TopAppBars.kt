package com.godlike.taskit.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.presentation.components.BackButton
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.TextPrimary
import com.godlike.taskit.ui.theme.TextSecondary
import com.godlike.taskit.ui.theme.taskItRed
import com.godlike.taskit.ui.theme.white

@Composable
fun TaskItTopAppBar() {
}

@Composable
fun TasksTopAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.taskit_logo),
            contentDescription = "logo",
            modifier = Modifier.size(40.dp)
        )
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 20.sp,
                color = TextPrimary,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = stringResource(R.string.tasks).uppercase(),
                fontSize = 10.sp,
                color = TextSecondary,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Bold,
            )
        }
        Box(
            Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable(onClick = {}),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "",
                tint = TextPrimary,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun LoginTopAppBar() {
    Column(
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

@Composable
fun SettingsTopAppBar(onBackButtonPress: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        BackButton(
            onClick = onBackButtonPress,
            modifier = Modifier.align(Alignment.CenterStart),
            size = 40.dp
        )
        Text(
            text = stringResource(R.string.settings),
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

@Preview
@Composable
fun PreviewSettingsTopAppBar() {
    SettingsTopAppBar {}
}