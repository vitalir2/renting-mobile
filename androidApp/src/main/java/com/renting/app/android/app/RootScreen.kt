package com.renting.app.android.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.renting.app.android.feature.home.HomeScreen
import com.renting.app.android.feature.login.LoginScreen
import com.renting.app.android.feature.registration.RegistrationScreen
import com.renting.app.feature.property.details.PropertyDetailsComponent
import com.renting.app.feature.root.component.RootComponent
import com.renting.app.feature.root.component.RootComponent.Child

@Composable
fun RootScreen(
    component: RootComponent,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val stack = component.childStack.subscribeAsState()
        RootNavigation(stack)
    }
}

@Composable
private fun RootNavigation(stack: State<ChildStack<*, Child>>) {
    Children(stack = stack.value) { child ->
        when (val screen = child.instance) {
            is Child.Login -> LoginScreen(component = screen.component)
            is Child.Home -> HomeScreen(component = screen.component)
            is Child.Registration -> RegistrationScreen(component = screen.component)
            is Child.PropertyDetails -> PropertyDetailsScreen(component = screen.component)
        }
    }
}

@Composable
fun PropertyDetailsScreen(component: PropertyDetailsComponent) {
    Text("Property details")
}
