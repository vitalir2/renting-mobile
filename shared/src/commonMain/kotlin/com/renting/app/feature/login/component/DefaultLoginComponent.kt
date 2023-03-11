package com.renting.app.feature.login.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.renting.app.core.utils.stateAsValue
import com.renting.app.feature.login.mvi.LoginStore.Intent
import com.renting.app.feature.login.mvi.LoginStoreFactory
import com.renting.app.feature.login.di.LoginGraph
import com.renting.app.feature.login.mvi.LoginStore
import com.renting.app.feature.login.mvi.stateToModel
import kotlinx.coroutines.launch

class DefaultLoginComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    loginGraph: LoginGraph,
    private val openMainScreen: () -> Unit,
    private val openRegistrationScreen: () -> Unit,
) : LoginComponent, ComponentContext by componentContext, LoginGraph by loginGraph {

    private val store =
        instanceKeeper.getStore {
            LoginStoreFactory(
                storeFactory = storeFactory,
                loginRepository = loginRepository,
            ).create()
        }

    init {
        coroutineScope.launch {
            store.labels.collect { label ->
                when (label) {
                    LoginStore.Label.LoggedSuccessfully -> onLoginCompleted()
                }
            }
        }
    }

    override val models: Value<LoginComponent.Model> = store.stateAsValue().map(stateToModel)

    override fun onLoginChanged(login: String) {
        store.accept(Intent.SetLogin(login))
    }

    override fun onPasswordChanged(password: String) {
        store.accept(Intent.SetPassword(password))
    }

    override fun onLoginStarted() {
        store.accept(Intent.StartLogin)
    }

    override fun onLoginCompleted() {
        openMainScreen()
    }

    override fun onRegistrationRequested() {
        openRegistrationScreen()
    }

    override fun onLoginErrorShowed() {
        store.accept(Intent.LoginErrorShowed)
    }
}
