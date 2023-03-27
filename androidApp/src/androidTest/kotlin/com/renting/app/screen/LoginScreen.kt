package com.renting.app.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.renting.app.android.feature.login.LoginScreenTags
import com.renting.app.core.clickOnTaggedText
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class LoginScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<LoginScreen>(
        semanticsProvider = semanticsProvider,
    ) {

    private val loginInput: KNode = child {
        hasTestTag(LoginScreenTags.LOGIN_INPUT)
    }

    private val signUpText: KNode = child {
        hasTestTag(LoginScreenTags.SIGN_UP_TEXT)
    }

    fun isVisible() {
        loginInput {
            assertIsDisplayed()
        }
    }

    fun openRegistrationScreen() {
        signUpText {
            clickOnTaggedText(LoginScreenTags.SIGN_UP_SPAN_TAG)
        }
    }
}
