package com.renting.app.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class MainScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<MainScreen>(
    semanticsProvider = semanticsProvider,
) {

    private val text: KNode = child {
        hasText("Android", substring = true)
    }

    fun isVisible() {
        text {
            assertIsDisplayed()
        }
    }
}
