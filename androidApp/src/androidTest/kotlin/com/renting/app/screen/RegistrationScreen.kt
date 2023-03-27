package com.renting.app.screen

import androidx.compose.ui.test.IdlingResource
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.renting.app.android.core.uikit.form.errorTestTag
import com.renting.app.core.form.TextField
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<RegistrationScreen>(
    semanticsProvider = semanticsProvider,
) {

    private val loginError: KNode = child {
        hasTestTag(TextField(TextField.Kind.LOGIN).errorTestTag)
    }

    private val actionButton: KNode = child {
        hasText("Register")
    }

    fun startRegistration() {
        actionButton.performClick()
    }

    fun awaitRegistrationEnd(composeTestRule: ComposeTestRule) {
        with(composeTestRule) {
            // TODO replace by awaiting of some action in compose here e.g. -- progress bar on the button is hidden for 500 ms
            registerIdlingResource(DelayIdlingResource(1_000))
            waitForIdle()
        }
    }

    fun formHasAnyError() {
        loginError {
            assertIsDisplayed()
        }
    }
}

class DelayIdlingResource(private val timeMs: Long) : IdlingResource {
    private var isIdle = false
    private val coroutineScope = MainScope()

    override val isIdleNow: Boolean
        get() = isIdle

    init {
        coroutineScope.launch {
            delay(timeMs)
            isIdle = true
        }
    }

}
