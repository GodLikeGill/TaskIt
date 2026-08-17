package com.godlike.taskit.presentation.auth

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginModalSheetContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun entering_email_and_password_and_clicking_login_triggers_onSignInWithEmail() {
        var capturedEmail = ""
        var capturedPassword = ""

        composeTestRule.setContent {
            LoginModalSheetContent(
                onClose = {},
                authState = AuthState.Idle,
                onSignInWithEmail = { email, password ->
                    capturedEmail = email
                    capturedPassword = password
                }
            )
        }

        composeTestRule.onNodeWithTag("email_field").performTextInput("john@gmail.com")
        composeTestRule.onNodeWithTag("password_field").performTextInput("password")
        composeTestRule.onNodeWithTag("login_button").performClick()

        assertEquals("john@gmail.com", capturedEmail)
        assertEquals("password", capturedPassword)
    }

    @Test
    fun loading_indicator_is_displayed_when_authState_is_loading() {
        composeTestRule.setContent {
            LoginModalSheetContent(
                onClose = {},
                authState = AuthState.Loading,
                onSignInWithEmail = { _, _ -> }
            )
        }
        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
    }

    @Test
    fun error_text_is_displayed_when_authstate_is_error() {
        composeTestRule.setContent {
            LoginModalSheetContent(
                onClose = {},
                authState = AuthState.Error("Network error"),
                onSignInWithEmail = { _, _ -> }
            )
        }
        composeTestRule.onNodeWithTag("error_text").assertIsDisplayed()
    }
}