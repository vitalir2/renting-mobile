package com.renting.app.feature.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.renting.app.feature.login.component.LoginComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Login(val component: LoginComponent) : Child
    }
}
