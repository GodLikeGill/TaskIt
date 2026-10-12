package com.godlike.taskit.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.ui.theme.Primary
import com.godlike.taskit.ui.theme.PrimaryDark
import com.godlike.taskit.ui.theme.PrimaryLow
import com.godlike.taskit.ui.theme.Roboto
import com.godlike.taskit.ui.theme.Surface
import com.godlike.taskit.ui.theme.SurfaceContainer
import com.godlike.taskit.ui.theme.TextPrimary
import com.godlike.taskit.ui.theme.TextSecondary
import com.godlike.taskit.util.SignInTopAppBar

@Composable
fun SignInScreen() {
    SignInScreenContent()
}

@Composable
fun SignInScreenContent() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Surface)
            .padding(10.dp),
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            SignInTopAppBar(onBackButtonPress = {})

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .blur(40.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Primary.copy(alpha = 0.4f), Color.Transparent
                                    )
                                )
                            )
                    )
                    Image(
                        painter = painterResource(R.drawable.taskit_logo),
                        contentDescription = "logo",
                        modifier = Modifier.size(75.dp)
                    )
                }

                Text(
                    text = "Welcome Back",
                    color = TextPrimary,
                    fontFamily = Roboto,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Sign in to sync your tasks, settings, and workspace across devices.",
                    color = PrimaryLow,
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = SurfaceContainer
                        )
                        .clickable {}
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(R.drawable.google_logo),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Continue with Google",
                        color = TextPrimary,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.W600
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = PrimaryLow)
                Text(text = "or sign in with email", color = PrimaryLow)
                HorizontalDivider(modifier = Modifier.weight(1f), color = PrimaryLow)
            }
            Column {
                Text(
                    text = "Email Address",
                    color = TextSecondary
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SurfaceContainer,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Email Icon",
                        tint = PrimaryLow,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    BasicTextField(
                        value = email,
                        onValueChange = { email = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        decorationBox = { innerTextField ->
                            if (email.isEmpty()) {
                                Text(
                                    text = "name@example.com",
                                    color = PrimaryLow,
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Column {
                Row {
                    Text(
                        text = "Password", color = TextSecondary
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Forgot Password", color = Primary
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SurfaceContainer,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Password Icon",
                        tint = PrimaryLow,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        decorationBox = { innerTextField ->
                            if (password.isEmpty()) {
                                Text(
                                    text = "• • • • • • • • • •",
                                    color = PrimaryLow,
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        },
                        visualTransformation = if (isPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                        tint = PrimaryLow,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = checked,
                    onCheckedChange = {},
                    colors = SwitchDefaults.colors(
                        checkedIconColor = PrimaryDark,
                        checkedThumbColor = PrimaryDark,
                        checkedTrackColor = Primary,
                        checkedBorderColor = Primary,
                        uncheckedIconColor = Primary,
                        uncheckedThumbColor = Primary,
                        uncheckedTrackColor = PrimaryLow,
                        uncheckedBorderColor = PrimaryLow
                    ),
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "Stay signed in for 30 days",
                    color = PrimaryLow
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Primary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { }
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sign in".uppercase(),
                    color = PrimaryDark,
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Don't have an account?",
                    color = PrimaryLow,
                    fontSize = 14.sp
                )
                Text(
                    text = "Create Account",
                    color = Primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "By signing in, you agree to the Terms of Service & Privacy Policy",
                    color = PrimaryLow,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignInScreenContent() {
    SignInScreenContent()
}