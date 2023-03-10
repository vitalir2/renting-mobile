package com.renting.app.feature.root.di

import com.renting.app.core.coroutines.createIODispatcher
import com.renting.app.core.network.createHttpClient
import com.renting.app.core.utils.Environment
import com.renting.app.feature.login.di.DefaultLoginGraph
import com.renting.app.feature.login.di.LoginGraph
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class DefaultRootGraph : RootGraph {

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

    override val loginGraph: LoginGraph = DefaultLoginGraph(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
    )
}
