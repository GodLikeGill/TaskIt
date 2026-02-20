@file:OptIn(ExperimentalMaterial3Api::class)

package com.godlike.taskit.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.R
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.darkGray
import com.godlike.taskit.ui.theme.modalSheetBackground
import com.godlike.taskit.ui.theme.taskItRed
import com.godlike.taskit.ui.theme.textFieldBackground
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
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .size(48.dp)
                .border(
                    width = 1.dp,
                    color = white.copy(alpha = 0.1f),
                    shape = CircleShape
                )
                .background(
                    color = white.copy(alpha = 0.05f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = white
            )
        }
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
            modifier = Modifier.padding(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.your_email),
                    fontSize = 14.sp,
                    color = darkGray,
                    fontFamily = InterFontFamily,
                    modifier = Modifier.padding(start = 14.dp, bottom = 4.dp)
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = stringResource(id = R.string.email)) },
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = textFieldBackground,
                        focusedContainerColor = textFieldBackground,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.your_password),
                    fontSize = 14.sp,
                    color = darkGray,
                    fontFamily = InterFontFamily,
                    modifier = Modifier.padding(start = 14.dp, bottom = 4.dp)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.password)) },
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = textFieldBackground,
                        focusedContainerColor = textFieldBackground,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                )
            }
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

/*
@Preview
@Composable
fun PreviewLoginModalSheetContent() {
    LoginModalSheetContent(
        focusRequester = FocusRequester(null),
        onClose = {},
    )
}*/
