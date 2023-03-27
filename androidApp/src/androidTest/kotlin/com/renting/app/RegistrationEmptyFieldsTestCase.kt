package com.renting.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.renting.app.android.app.MainActivity
import com.renting.app.core.ComposeTestCase
import com.renting.app.screen.LoginScreen
import com.renting.app.screen.RegistrationScreen
import org.junit.Rule
import org.junit.Test

class RegistrationEmptyFieldsTestCase : ComposeTestCase() {

    @get:Rule
    override val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun run() = run {
        step("Open Registration screen from the Login") {
            onComposeScreen<LoginScreen> {
                openRegistrationScreen()
            }
        }
        step("Click on the action button and await registration response") {
            onComposeScreen<RegistrationScreen> {
                startRegistration()
                awaitRegistrationEnd(composeTestRule)
            }
        }
        step("Check that form has any error") {
            onComposeScreen<RegistrationScreen> {
                formHasAnyError()
            }
        }
    }
}