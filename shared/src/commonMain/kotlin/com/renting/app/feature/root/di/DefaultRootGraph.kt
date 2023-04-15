package com.renting.app.feature.root.di

import com.renting.app.core.auth.di.AuthGraph
import com.renting.app.core.auth.di.DefaultAuthGraph
import com.renting.app.core.coroutines.createIODispatcher
import com.renting.app.core.logger.LoggerStore
import com.renting.app.core.network.createHttpClient
import com.renting.app.core.settings.SettingKey
import com.renting.app.core.settings.SettingsFactory
import com.renting.app.core.utils.Environment
import com.renting.app.feature.home.DefaultHomeGraph
import com.renting.app.feature.home.HomeGraph
import com.renting.app.feature.login.di.DefaultLoginGraph
import com.renting.app.feature.login.di.LoginGraph
import com.renting.app.feature.recommendation.DefaultRecommendationGraph
import com.renting.app.feature.recommendation.RecommendationGraph
import com.renting.app.feature.registration.di.DefaultRegistrationGraph
import com.renting.app.feature.registration.di.RegistrationGraph
import com.russhwolf.settings.ObservableSettings
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class DefaultRootGraph(
    private val settingsFactory: SettingsFactory,
) : RootGraph {

    private val ioDispatcher = createIODispatcher()

    private val settings: ObservableSettings by lazy {
        settingsFactory.create()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val httpClient = createHttpClient().config {
        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    LoggerStore.logger.info { message }
                }
            }
        }
        defaultRequest {
            url(Environment.PRODUCTION_NETWORK_HOST)
            headers {
                settings.getStringOrNull(SettingKey.AUTH_TOKEN)?.let { token ->
                    append(HttpHeaders.Authorization, token)
                }
            }
        }
    }

    override val authGraph: AuthGraph = DefaultAuthGraph(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
        settings = settings,
    )

    override val loginGraph: LoginGraph
        get() = DefaultLoginGraph(
            authGraph = authGraph,
        )
    override val registrationGraph: RegistrationGraph
        get() = DefaultRegistrationGraph(
            authGraph = authGraph,
        )

    override val homeGraph: HomeGraph
        get() = DefaultHomeGraph(
            authGraph = authGraph,
            recommendationGraph = recommendationGraph,
        )

    override val recommendationGraph: RecommendationGraph = DefaultRecommendationGraph(
        httpClient = httpClient,
        ioDispatcher = ioDispatcher,
    )
}
