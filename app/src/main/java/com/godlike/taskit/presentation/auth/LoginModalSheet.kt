@file:OptIn(ExperimentalMaterial3Api::class)

package com.godlike.taskit.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.presentation.components.AuthTextField
import com.godlike.taskit.presentation.components.CloseButton
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.darkGray
import com.godlike.taskit.ui.theme.modalSheetBackground
import com.godlike.taskit.ui.theme.taskItRed
import com.godlike.taskit.ui.theme.white


@Composable
fun LoginModalSheet(
    sheetState: SheetState,
    onClose: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(sheetState.currentValue) {
        if (sheetState.currentValue == SheetValue.Expanded) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    LoginModalSheetContent(
        focusRequester = focusRequester,
        onClose = onClose
    )
}

@Composable
fun LoginModalSheetContent(
    focusRequester: FocusRequester,
    onClose: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = modalSheetBackground)
            .padding(16.dp)
    ) {
        CloseButton { onClose }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.log_in),
                color = white,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = InterFontFamily,
            )
            Text(
                text = stringResource(id = R.string.login_description),
                color = darkGray,
                fontSize = 14.sp,
                fontFamily = InterFontFamily,
            )
        }
        Column(
            modifier = Modifier.padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            AuthTextField(
                value = email,
                title = stringResource(R.string.your_email),
                label = stringResource(R.string.email),
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )
            AuthTextField(
                value = password,
                title = stringResource(R.string.your_password),
                label = stringResource(R.string.password),
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth()
            )
            Column {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = taskItRed)
                ) {
                    Text(
                        text = stringResource(R.string.log_in),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = InterFontFamily,
                    )
                }
                Text(
                    text = stringResource(id = R.string.forgot_your_password),
                    fontSize = 14.sp,
                    color = darkGray,
                    fontFamily = InterFontFamily,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .clickable {
                            /*   Called when user forgets password   */
                        },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginModalSheetContent() {
    val focusRequester = remember { FocusRequester() }
    LoginModalSheetContent(focusRequester = focusRequester, onClose = {})
}
