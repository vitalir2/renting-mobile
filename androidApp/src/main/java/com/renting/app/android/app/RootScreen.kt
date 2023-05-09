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
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.renting.app.android.feature.home.HomeScreen
import com.renting.app.android.feature.login.LoginScreen
import com.renting.app.android.feature.property.details.PropertyDetailsScreen
import com.renting.app.android.feature.registration.RegistrationScreen
import com.renting.app.android.feature.search.results.SearchResultsScreen
import com.renting.app.feature.filters.FiltersComponent
import com.renting.app.feature.root.component.RootComponent
import com.renting.app.feature.root.component.RootComponent.Child

@Composable
fun RootScreen(
    component: RootComponent,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        val stack = component.childStack.subscribeAsState()
        RootNavigation(stack)
    }
}

@Composable
private fun RootNavigation(stack: State<ChildStack<*, Child>>) {
    val systemUiController = rememberSystemUiController()

    Children(stack = stack.value) { child ->
        setupSystemUi(child.instance, systemUiController)
        when (val screen = child.instance) {
            is Child.Login -> LoginScreen(component = screen.component)
            is Child.Home -> HomeScreen(component = screen.component)
            is Child.Registration -> RegistrationScreen(component = screen.component)
            is Child.PropertyDetails -> PropertyDetailsScreen(component = screen.component)
            is Child.Filters -> FiltersScreen(component = screen.component)
            is Child.SearchResults -> SearchResultsScreen(component = screen.component)
        }
    }
}

private fun setupSystemUi(
    child: Child,
    systemUiController: SystemUiController
) {
    val showStatusBar = when (child) {
        is Child.PropertyDetails -> false
        else -> true
    }
    systemUiController.apply {
        isStatusBarVisible = showStatusBar
    }
}

@Composable
fun FiltersScreen(component: FiltersComponent) {
    Text("Filters")
}
