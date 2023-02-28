package com.renting.app.feature.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.core.utils.stateAsValue

class DefaultLoginComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : LoginComponent, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            LoginStoreFactory(
                storeFactory = storeFactory,
            ).create()
        }

    override val models: Value<LoginComponent.Model> = store.stateAsValue().map(stateToModel)

    override fun onLoginInputChanged(login: String) {
        store.accept(LoginStore.Intent.SetLogin(login))
    }

    override fun onPasswordInputChanged(password: String) {
        store.accept(LoginStore.Intent.SetPassword(password))
    }
}
