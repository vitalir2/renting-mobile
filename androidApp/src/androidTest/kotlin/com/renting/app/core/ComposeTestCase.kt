package com.renting.app.core

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule

abstract class ComposeTestCase : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(),
) {

    @get:Rule
    abstract val composeTestRule: ComposeTestRule

    inline fun <reified T : ComposeScreen<T>> onComposeScreen(
        noinline function: T.() -> Unit
    ): T {
        return T::class.java
            .getDeclaredConstructor(
                SemanticsNodeInteractionsProvider::class.java
            )
            .newInstance(composeTestRule)
            .apply { this(function) }
    }
}
