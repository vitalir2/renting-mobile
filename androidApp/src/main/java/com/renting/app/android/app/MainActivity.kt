package com.renting.app.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.core.settings.SettingsFactory
import com.renting.app.feature.root.component.DefaultRootComponent
import com.renting.app.feature.root.component.RootComponent
import com.renting.app.feature.root.di.DefaultRootGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = createRootComponent(defaultComponentContext())

        setContent {
            RentingTheme {
                RootScreen(root)
            }
        }
    }

    private fun createRootComponent(
        componentContext: ComponentContext,
    ): RootComponent = DefaultRootComponent(
        componentContext = componentContext,
        storeFactory = DefaultStoreFactory(),
        rootGraph = DefaultRootGraph(
            settingsFactory = SettingsFactory(applicationContext),
        ),
    )
}
