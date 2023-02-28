package com.renting.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.renting.app.android.app.MainActivity
import com.renting.app.core.ComposeTestCase
import com.renting.app.screen.LoginScreen
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchAppTest : ComposeTestCase() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLaunch() = run {
        step("Assert first screen is visible") {
            onComposeScreen<LoginScreen>(composeTestRule) {
                isVisible()
            }
        }
    }
}
