package com.renting.app.feature.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.core.coroutines.createIODispatcher
import com.renting.app.core.network.createHttpClient
import com.renting.app.core.utils.Environment
import com.renting.app.core.utils.stateAsValue
import com.renting.app.feature.login.LoginStore.Intent
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class DefaultLoginComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : LoginComponent, ComponentContext by componentContext {

    @OptIn(ExperimentalSerializationApi::class)
    private val store =
        instanceKeeper.getStore {
            LoginStoreFactory(
                storeFactory = storeFactory,
                loginRepository = DefaultLoginRepository(
                    // TODO RENTING-4: Move to DI graph
                    httpClient = createHttpClient().config {
                        install(ContentNegotiation) {
                            json(Json {
                                explicitNulls = false
                            })
                        }
                        defaultRequest {
                            url(Environment.PRODUCTION_NETWORK_HOST)
                        }
                    },
                    ioDispatcher = createIODispatcher(),
                ),
            ).create()
        }

    override val models: Value<LoginComponent.Model> = store.stateAsValue().map(stateToModel)

    override fun onLoginInputChanged(login: String) {
        store.accept(Intent.SetLogin(login))
    }

    override fun onPasswordInputChanged(password: String) {
        store.accept(Intent.SetPassword(password))
    }

    override fun onLoginStarted() {
        store.accept(Intent.StartLogin)
    }
}
