package com.godlike.taskit.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpModalSheet(
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

    SignUpModalSheetContent(
        focusRequester = focusRequester,
        onClose = onClose
    )
}

@Composable
fun SignUpModalSheetContent(
    focusRequester: FocusRequester,
    onClose: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
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
                text = stringResource(id = R.string.sign_up),
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
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AuthTextField(
                    value = firstName,
                    title = stringResource(R.string.your_first_name),
                    label = stringResource(R.string.first_name),
                    onValueChange = { firstName = it },
                    modifier = Modifier.focusRequester(focusRequester),
                    columnModifier = Modifier.weight(1f)
                )
                AuthTextField(
                    value = lastName,
                    title = stringResource(R.string.your_last_name),
                    label = stringResource(R.string.last_name),
                    onValueChange = { lastName = it },
                    columnModifier = Modifier.weight(1f)
                )
            }
            AuthTextField(
                value = email,
                title = stringResource(R.string.your_email),
                label = stringResource(R.string.email),
                onValueChange = { email = it },
            )
            AuthTextField(
                value = password,
                title = stringResource(R.string.your_password),
                label = stringResource(R.string.password),
                onValueChange = { password = it },
            )
            Column {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = taskItRed)
                ) {
                    Text(
                        text = stringResource(R.string.sign_up),
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
fun PreviewSignUpModalSheetContent() {
    val focusRequester = remember { FocusRequester() }
    SignUpModalSheetContent(focusRequester = focusRequester, onClose = {})
}
