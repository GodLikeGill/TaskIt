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
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
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
fun SignUpScreen(
    onAuthSuccess: () -> Unit,
    onBackButtonClick: () -> Unit,
    onSignInButtonClick: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsState()

    SignUpScreenContent(
        authState = authState,
        onBackButtonClick = onBackButtonClick,
        onSignInButtonClick = onSignInButtonClick,
        onSignUpButtonClick = { firstName, lastName, email, password ->
            viewModel.register(
                firstName = firstName, lastName = lastName, email = email, password = password
            )
        })

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) onAuthSuccess()
    }
}

@Composable
fun SignUpScreenContent(
    authState: AuthState,
    onBackButtonClick: () -> Unit,
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: (String, String, String, String) -> Unit,
) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    val isFormValid =
        firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && isChecked

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
            SignInTopAppBar(onBackButtonPress = onBackButtonClick)

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .blur(30.dp)
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
                        modifier = Modifier.size(50.dp)
                    )
                }

                Text(
                    text = "Create Your Account",
                    color = TextPrimary,
                    fontFamily = Roboto,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Start organizing, scheduling, and syncing your tasks seamlessly across devices.",
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
                            shape = RoundedCornerShape(10.dp), color = SurfaceContainer
                        )
                        .clickable {}
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        5.dp, Alignment.CenterHorizontally
                    )) {
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
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(0.1.dp), color = PrimaryLow
                )
                Text(text = "or register with email", color = PrimaryLow)
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(0.1.dp), color = PrimaryLow
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "First Name", color = TextSecondary
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = SurfaceContainer, shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Name Icon",
                            tint = PrimaryLow,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        BasicTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.PersonName, imeAction = ImeAction.Next
                            ),
                            decorationBox = { innerTextField ->
                                if (firstName.isEmpty()) {
                                    Text(
                                        text = "e.g. John", color = PrimaryLow, fontSize = 14.sp
                                    )
                                }
                                innerTextField()
                            },
                        )
                    }
                } // First name Field
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Last Name", color = TextSecondary
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = SurfaceContainer, shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Name Icon",
                            tint = PrimaryLow,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        BasicTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.PersonName, imeAction = ImeAction.Next
                            ),
                            decorationBox = { innerTextField ->
                                if (lastName.isEmpty()) {
                                    Text(
                                        text = "e.g. Doe", color = PrimaryLow, fontSize = 14.sp
                                    )
                                }
                                innerTextField()
                            },
                        )
                        Modifier.weight(1f)
                    }
                } // Last Name Field
            }
            Column {
                Text(
                    text = "Email Address", color = TextSecondary
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SurfaceContainer, shape = RoundedCornerShape(12.dp)
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
                            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                        ),
                        decorationBox = { innerTextField ->
                            if (email.isEmpty()) {
                                Text(
                                    text = "name@example.com", color = PrimaryLow, fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            } // Email Field
            Column {
                Text(
                    text = "Password", color = TextSecondary
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SurfaceContainer, shape = RoundedCornerShape(12.dp)
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
            } // Password Field
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = if (isChecked) Primary else SurfaceContainer,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { isChecked = !isChecked }, contentAlignment = Alignment.Center
                ) {
                    if (isChecked) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = "Checked",
                            tint = PrimaryDark,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "I agree to the Terms of Service and Privacy Policy.",
                    color = PrimaryLow,
                    fontSize = 14.sp,
                    fontFamily = Roboto
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Primary, shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(enabled = isFormValid) {
                        onSignUpButtonClick(firstName, lastName, email, password)
                    }
                    .padding(10.dp), horizontalArrangement = Arrangement.spacedBy(
                10.dp, Alignment.CenterHorizontally
            ), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Sign up".uppercase(),
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
            when (authState) {
                is AuthState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally), color = Primary
                    )
                }

                is AuthState.Error -> {
                    Text(
                        text = authState.message,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                else -> {}
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Already have an account?", color = PrimaryLow, fontSize = 14.sp
                )
                Text(
                    text = "Sign In",
                    color = Primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onSignInButtonClick() })
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreenContent() {
    SignUpScreenContent(
        authState = AuthState.Idle,
        onBackButtonClick = {},
        onSignInButtonClick = {},
        onSignUpButtonClick = { _, _, _, _ -> },
    )
}