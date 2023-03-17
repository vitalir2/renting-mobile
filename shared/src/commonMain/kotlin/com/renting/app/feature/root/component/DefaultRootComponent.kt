package com.renting.app.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popWhile
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.feature.home.DefaultHomeComponent
import com.renting.app.feature.home.HomeComponent
import com.renting.app.feature.login.component.DefaultLoginComponent
import com.renting.app.feature.login.component.LoginComponent
import com.renting.app.feature.registration.DefaultRegistrationComponent
import com.renting.app.feature.registration.RegistrationComponent
import com.renting.app.feature.root.component.RootComponent.Child
import com.renting.app.feature.root.di.RootGraph

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val rootGraph: RootGraph,
) : RootComponent, ComponentContext by componentContext, RootGraph by rootGraph {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = if (rootGraph.loginGraph.loginRepository.isLoggedIn()) {
                Configuration.Home
            } else {
                Configuration.Login
            },
            handleBackButton = true,
            childFactory = ::createChild,
        )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        is Configuration.Login -> Child.Login(
            createLoginComponent(componentContext)
        )
        is Configuration.Home -> Child.Home(
            createHomeComponent(componentContext)
        )
        is Configuration.Registration -> Child.Registration(
            createRegistrationComponent(componentContext)
        )
    }

    private fun createRegistrationComponent(componentContext: ComponentContext): RegistrationComponent {
        return DefaultRegistrationComponent(
            componentContext = componentContext,
            storeFactory = storeFactory,
            registrationGraph = registrationGraph,
            onRegistrationCompleted = {
                // Pop the whole auth stack; in the future - will create a separate auth graph
                navigation.popWhile { true }
                navigation.push(Configuration.Home)
            },
        )
    }

    private fun createHomeComponent(componentContext: ComponentContext): HomeComponent {
        return DefaultHomeComponent(
            componentContext = componentContext,
            storeFactory = storeFactory,
            homeGraph = homeGraph,
            onLoggedOutSuccessfully = {
                navigation.replaceCurrent(
                    configuration = Configuration.Login,
                )
            },
        )
    }

    private fun createLoginComponent(
        componentContext: ComponentContext,
    ): LoginComponent = DefaultLoginComponent(
        componentContext = componentContext,
        storeFactory = storeFactory,
        loginGraph = loginGraph,
        openMainScreen = {
            navigation.replaceCurrent(
                configuration = Configuration.Home,
            )
        },
        openRegistrationScreen = {
            navigation.push(
                configuration = Configuration.Registration,
            )
        }
    )

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Login : Configuration()

        @Parcelize
        object Registration : Configuration()

        @Parcelize
        object Home : Configuration()
    }
}
