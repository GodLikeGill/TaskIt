package com.godlike.taskit.presentation.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.Primary
import com.godlike.taskit.ui.theme.PrimaryLow
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.ui.theme.SurfaceContainer
import com.godlike.taskit.ui.theme.TextPrimary
import com.godlike.taskit.util.SettingsTopAppBar

@Composable
fun SettingsScreen(onLogoutButtonClick: () -> Unit) {
    SettingsScreenContent(onLogoutButtonClick = onLogoutButtonClick)
}

@Composable
fun SettingsScreenContent(onLogoutButtonClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Surface)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SettingsTopAppBar()
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row{
                Text(
                    text = "Profile & Account".uppercase(),
                    color = Primary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(SurfaceContainer)
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_picture),
                        contentDescription = "",
                        modifier = Modifier.size(64.dp)
                    )
                    Column(

                    ) {
                        Text(
                            text = "Guest User",
                            color = TextPrimary
                        )
                        Text(
                            text = "guest@device.local",
                            color = PrimaryLow
                        )
                        Text(
                            text = "Local Workspace Active",
                            color = PrimaryLow
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = Primary)
                        .padding(10.dp)
                        .clickable {},
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircleOutline,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = stringResource(R.string.sign_in_register),
                        fontSize = 15.sp,
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = "Sign in with Google or Email to preserve tasks",
                    color = PrimaryLow,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(onLogoutButtonClick = {})
}