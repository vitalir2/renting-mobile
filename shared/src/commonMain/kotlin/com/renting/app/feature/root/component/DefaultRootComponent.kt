package com.renting.app.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.feature.login.component.DefaultLoginComponent
import com.renting.app.feature.login.component.LoginComponent
import com.renting.app.feature.root.component.RootComponent.Child
import com.renting.app.feature.root.di.DefaultRootGraph
import com.renting.app.feature.root.di.RootGraph

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val rootGraph: RootGraph = DefaultRootGraph(),
) : RootComponent, ComponentContext by componentContext, RootGraph by rootGraph {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Configuration.Login,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        is Configuration.Login -> Child.Login(createLoginComponent(componentContext))
    }

    private fun createLoginComponent(
        componentContext: ComponentContext,
    ): LoginComponent = DefaultLoginComponent(
        componentContext = componentContext,
        storeFactory = storeFactory,
        loginGraph = loginGraph,
    )

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Login : Configuration()
    }
}
