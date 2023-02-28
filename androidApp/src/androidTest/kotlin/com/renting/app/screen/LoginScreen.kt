package com.renting.app.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.renting.app.android.feature.login.LoginScreenTags
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class LoginScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<LoginScreen>(
    semanticsProvider = semanticsProvider,
) {

    private val loginInput: KNode = child {
        hasTestTag(LoginScreenTags.LOGIN_INPUT)
    }

    fun isVisible() {
        loginInput {
            assertIsDisplayed()
        }
    }
}
