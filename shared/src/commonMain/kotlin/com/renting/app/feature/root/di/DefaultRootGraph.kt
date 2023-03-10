package com.renting.app.feature.root.di

import com.renting.app.core.coroutines.createIODispatcher
import com.renting.app.core.network.createHttpClient
import com.renting.app.core.settings.SettingsFactory
import com.renting.app.core.utils.Environment
import com.renting.app.feature.login.di.DefaultLoginGraph
import com.renting.app.feature.login.di.LoginGraph
import com.russhwolf.settings.ObservableSettings
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class DefaultRootGraph(
    private val settingsFactory: SettingsFactory,
) : RootGraph {

    @OptIn(ExperimentalSerializationApi::class)
    private val httpClient = createHttpClient().config {
        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
            })
        }
        defaultRequest {
            url(Environment.PRODUCTION_NETWORK_HOST)
        }
    }

    private val ioDispatcher = createIODispatcher()

    private val settings: ObservableSettings by lazy {
        settingsFactory.create()
    }

    override val loginGraph: LoginGraph = DefaultLoginGraph(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
        settings = settings,
    )
}
