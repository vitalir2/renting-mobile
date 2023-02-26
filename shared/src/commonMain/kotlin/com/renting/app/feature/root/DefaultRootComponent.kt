package com.renting.app.feature.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.feature.login.DefaultLoginComponent
import com.renting.app.feature.login.LoginComponent
import com.renting.app.feature.root.RootComponent.Child

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
) : RootComponent, ComponentContext by componentContext {

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
    )

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Login : Configuration()
    }
}
