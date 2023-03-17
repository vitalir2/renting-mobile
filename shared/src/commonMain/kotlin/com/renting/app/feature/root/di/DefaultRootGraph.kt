package com.renting.app.feature.root.di

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.core.auth.di.DefaultAuthGraph
import com.renting.app.core.coroutines.createIODispatcher
import com.renting.app.core.network.createHttpClient
import com.renting.app.core.settings.SettingsFactory
import com.renting.app.core.utils.Environment
import com.renting.app.feature.home.DefaultHomeGraph
import com.renting.app.feature.home.HomeGraph
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

    override val authGraph: AuthGraph = DefaultAuthGraph(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
        settings = settings,
    )

    override val loginGraph: LoginGraph get() = DefaultLoginGraph(
        authGraph = authGraph,
    )

    override val homeGraph: HomeGraph get() = DefaultHomeGraph(
        authGraph = authGraph,
    )
}
