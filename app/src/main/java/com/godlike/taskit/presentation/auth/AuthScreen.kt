package com.godlike.taskit.presentation.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.godlike.taskit.R
import com.godlike.taskit.presentation.components.SocialLoginButton
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.facebookBlue
import com.godlike.taskit.ui.theme.grayBackground
import com.godlike.taskit.ui.theme.lightGray
import com.godlike.taskit.ui.theme.modalSheetBackground
import com.godlike.taskit.ui.theme.taskItRed
import com.godlike.taskit.ui.theme.white
import com.godlike.taskit.util.LoginTopAppBar

@Composable
fun AuthScreen(
    onAuthSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val authState by viewModel.authState.collectAsState()

    AuthScreenContent(
        authState = authState,
        onSignInWithEmail = { email, password -> viewModel.login(email, password) },
        onSignUpWithEmail = { email, password, firstName, lastName -> viewModel.register(email, password, firstName, lastName) },
        onContinueWithGoogle = {},
        onContinueWithFacebook = {},
    )

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onAuthSuccess()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthScreenContent(
    authState: AuthState,
    onSignInWithEmail: (String, String) -> Unit,
    onSignUpWithEmail: (String, String, String, String) -> Unit,
    onContinueWithGoogle: () -> Unit,
    onContinueWithFacebook: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = { LoginTopAppBar() }
    ) { paddingValues ->

        var expanded by remember { mutableStateOf(false) }
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var showLoginModalSheet by remember { mutableStateOf(false) }
        var showSignUpModalSheet by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 16.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    expanded = false
                },
        ) {
            Text(
                text = stringResource(R.string.app_slogan),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center,
                color = white,
                fontSize = 16.sp,
                fontFamily = InterFontFamily,
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val buttonModifier = Modifier.fillMaxWidth(0.7f)

                AnimatedContent(
                    targetState = expanded, transitionSpec = {
                        fadeIn(tween(200)) + expandVertically() togetherWith fadeOut(tween(150)) + shrinkVertically()
                    }, label = "Auth Button Animation"
                ) { isExpanded ->
                    if (!isExpanded) {
                        SocialLoginButton(
                            modifier = buttonModifier,
                            text = stringResource(R.string.continue_with_email),
                            painter = painterResource(R.drawable.email_logo),
                            bgColor = taskItRed,
                            onClick = { expanded = true },
                        )
                    } else {
                        Column(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = white.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(
                                    color = white.copy(alpha = 0.05f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            SocialLoginButton(
                                modifier = Modifier,
                                text = stringResource(id = R.string.log_in_with_email),
                                painter = painterResource(R.drawable.email_logo),
                                bgColor = Color.Transparent,
                                onClick = { showLoginModalSheet = true })
                            SocialLoginButton(
                                modifier = Modifier,
                                text = stringResource(id = R.string.sign_up_with_email),
                                painter = rememberVectorPainter(Icons.Default.Create),
                                iconColor = white,
                                bgColor = Color.Transparent,
                                onClick = { showSignUpModalSheet = true })
                        }
                    }
                }
                SocialLoginButton(
                    modifier = buttonModifier,
                    text = stringResource(id = R.string.continue_with_google),
                    painter = painterResource(R.drawable.google_logo),
                    bgColor = grayBackground,
                    onClick = onContinueWithGoogle
                )
                SocialLoginButton(
                    modifier = buttonModifier,
                    text = stringResource(id = R.string.continue_with_facebook),
                    painter = painterResource(R.drawable.facebook_logo),
                    bgColor = facebookBlue,
                    onClick = onContinueWithFacebook
                )
                Text(
                    modifier = Modifier.clickable {},
                    text = stringResource(R.string.new_user),
                    textAlign = TextAlign.Center,
                    color = lightGray,
                    fontSize = 14.sp,
                    fontFamily = InterFontFamily,
                    textDecoration = TextDecoration.Underline
                )
            }

            if (showLoginModalSheet || showSignUpModalSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showLoginModalSheet = false
                        showSignUpModalSheet = false
                    }, sheetState = sheetState, containerColor = modalSheetBackground
                ) {
                    if (showLoginModalSheet) LoginModalSheet(
                        authState = authState,
                        onSignInWithEmail = onSignInWithEmail,
                        onClose = { showLoginModalSheet = false })
                    if (showSignUpModalSheet) SignUpModalSheet(
                        authState = authState,
                        onSignUpWithEmail = onSignUpWithEmail,
                        onClose = { showSignUpModalSheet = false })
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreenContent() {
    AuthScreenContent(
        authState = AuthState.Loading,
        onSignInWithEmail = { _, _ -> },
        onSignUpWithEmail = { _, _, _, _ -> },
        onContinueWithGoogle = {},
        onContinueWithFacebook = {},
    )
}