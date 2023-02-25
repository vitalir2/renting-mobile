package com.renting.app.feature.login

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.flow.map

class LoginController(lifecycle: Lifecycle) {

    private val store = LoginStoreFactory(DefaultStoreFactory()).create()

    init {
        lifecycle.doOnDestroy(store::dispose)
    }

    fun onViewCreated(view: LoginScreen, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            store.states.map(stateToModel) bindTo view
            view.events.map(eventToIntent) bindTo store
        }
    }
}
