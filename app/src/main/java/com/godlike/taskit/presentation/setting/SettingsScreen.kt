package com.godlike.taskit.presentation.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.presentation.components.SettingsTextField
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.taskItRed
import com.godlike.taskit.util.SettingsTopAppBar

@Composable
fun SettingsScreen(onLogoutButtonClick: () -> Unit) {
    SettingsScreenContent(onLogoutButtonClick = onLogoutButtonClick)
}

@Composable
fun SettingsScreenContent(onLogoutButtonClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = colorResource(R.color.background),
        topBar = { SettingsTopAppBar {} }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Default Profile Picture",
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.profile),
                    color = taskItRed,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = InterFontFamily,
                )
                SettingsTextField(
                    imageVector1 = Icons.Filled.AccountCircle,
                    text = "Username",
                    textResult = "John Doe",
                    onClick = {})
                SettingsTextField(
                    imageVector1 = Icons.Filled.Email,
                    text = "Email",
                    textResult = "johndoe@gmail.com",
                    onClick = {})
                SettingsTextField(
                    imageVector1 = Icons.Filled.Lock,
                    text = "Password",
                    textResult = "••••••••",
                    onClick = {})
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.account),
                    color = taskItRed,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = InterFontFamily,
                )
                SettingsTextField(
                    text = stringResource(R.string.logout),
                    imageVector2 = Icons.AutoMirrored.Filled.ExitToApp,
                    onClick = { onLogoutButtonClick() })
            }
        }
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(onLogoutButtonClick = {})
}