package com.godlike.taskit.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.facebookBlue
import com.godlike.taskit.ui.theme.grayBackground
import com.godlike.taskit.ui.theme.taskItRed
import com.godlike.taskit.ui.theme.white
import com.godlike.taskit.util.LoginTopAppBar

@Composable
fun LoginScreen() {
    LoginScreenContent(
        onSignInWithEmail = {},
        onContinueWithGoogle = {},
        onContinueWithFacebook = {},
    )
}

@Composable
fun LoginScreenContent(
    onSignInWithEmail: () -> Unit,
    onContinueWithGoogle: () -> Unit,
    onContinueWithFacebook: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = { LoginTopAppBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 16.dp),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text(
                    text = stringResource(R.string.app_slogan),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = white,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily,
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            )
            {
                val buttonModifier = Modifier.fillMaxWidth(0.7f)

                Button(
                    modifier = buttonModifier,
                    onClick = onSignInWithEmail,
                    colors = ButtonDefaults.buttonColors(containerColor = taskItRed)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.email_logo),
                        contentDescription = "Email logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.sign_in_with_email),
                        fontSize = 16.sp,
                        fontFamily = InterFontFamily
                    )
                }
                Button(
                    modifier = buttonModifier,
                    onClick = onContinueWithGoogle,
                    colors = ButtonDefaults.buttonColors(containerColor = grayBackground)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = "Google Logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.continue_with_google),
                        fontSize = 16.sp,
                        fontFamily = InterFontFamily,
                    )
                }
                Button(
                    modifier = buttonModifier,
                    onClick = onContinueWithFacebook,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = facebookBlue
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook_logo),
                        contentDescription = "Facebook logo",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.continue_with_google),
                        fontSize = 16.sp,
                        color = white,
                        fontFamily = InterFontFamily,
                    )
                }
                Text(
                    modifier = buttonModifier.clickable{},
                    text = stringResource(R.string.new_user),
                    textAlign = TextAlign.Center,
                    color = white,
                    fontSize = 14.sp,
                    fontFamily = InterFontFamily
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreenContent() {
    LoginScreenContent(
        onSignInWithEmail = {},
        onContinueWithGoogle = {},
        onContinueWithFacebook = {},
    )
}