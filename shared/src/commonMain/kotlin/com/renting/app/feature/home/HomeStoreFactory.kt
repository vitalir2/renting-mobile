package com.renting.app.feature.home

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.auth.repository.UserRepository
import com.renting.app.core.monad.Either
import com.renting.app.feature.home.HomeStore.Intent
import com.renting.app.feature.home.HomeStore.Label
import com.renting.app.feature.home.HomeStore.State
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.recommendation.RecommendationRepository
import kotlinx.coroutines.launch

internal class HomeStoreFactory(
    private val storeFactory: StoreFactory,
    homeGraph: HomeGraph,
) : HomeGraph by homeGraph {

    fun create(): HomeStore =
        object : HomeStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "HomeStore",
                initialState = State(),
                executorFactory = {
                    ExecutorImpl(
                        authRepository = authRepository,
                        userRepository = userRepository,
                        recommendationRepository = recommendationRepository,
                    )
                },
                bootstrapper = BootstrapperImpl(),
                reducer = ReducerImpl(),
            ) {}

    private sealed interface Action {
        object InitProfile : Action
        object InitRecommendations : Action
        object InitQuickFilters : Action
    }

    private sealed interface Msg {
        data class UserInfoReceived(val userInfo: UserInfo) : Msg
        data class Recommendations(val value: List<PropertySnippet>) : Msg
        data class UpdatedQuickFilter(val type: PropertyType) : Msg
        data class QuickFilters(val quickFilters: PropertyTypeQuickFilters) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {

        override fun invoke() {
            dispatch(Action.InitProfile)
            dispatch(Action.InitRecommendations)
            dispatch(Action.InitQuickFilters)
        }
    }

    private class ExecutorImpl(
        private val authRepository: AuthRepository,
        private val userRepository: UserRepository,
        private val recommendationRepository: RecommendationRepository,
    ) : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.Logout -> logout()
                is Intent.ToggleTypeQuickFilter -> dispatch(
                    Msg.UpdatedQuickFilter(intent.type)
                )
                Intent.ClearTypeQuickFiltersSelection -> {
                    dispatch(
                        // TODO RENTING-44: remove double bang by creating more than one screen state
                        Msg.QuickFilters(getState().recommendationQuickFilters!!.clearSelection())
                    )
                }
            }
        }

        private fun logout() {
            scope.launch {
                when (authRepository.logout()) {
                    is Either.Left -> {
                        // Nothing for now
                    }
                    is Either.Right -> {
                        publish(Label.LoggedOutSuccessfully)
                    }
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.InitProfile -> initProfile()
                Action.InitRecommendations -> initRecommendations()
                Action.InitQuickFilters -> initQuickFilters()
            }
        }

        private fun initQuickFilters() {
            val quickFilters = PropertyTypeQuickFilters.filtersOrder
                .map(::PropertyTypeQuickFilter)
                .let(::PropertyTypeQuickFilters)
            dispatch(Msg.QuickFilters(quickFilters))
        }

        private fun initRecommendations() {
            scope.launch {
                when (val recommendations = recommendationRepository.getRecommendations()) {
                    is Either.Left -> {
                        // TODO add error handling
                    }
                    is Either.Right -> {
                        dispatch(Msg.Recommendations(recommendations.value))
                    }
                }
            }
        }

        private fun initProfile() {
            scope.launch {
                when (val userInfo = userRepository.getUserInfo()) {
                    is Either.Left -> {
                        // Nothing for now
                    }
                    is Either.Right -> dispatch(Msg.UserInfoReceived(userInfo.value))
                }
            }
        }
    }

    private class ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.UserInfoReceived -> copy(userInfo = msg.userInfo)
                is Msg.Recommendations -> copy(
                    recommendations = msg.value,
                )
                is Msg.UpdatedQuickFilter -> copy(
                    recommendationQuickFilters = recommendationQuickFilters?.setActive(msg.type),
                )
                is Msg.QuickFilters -> copy(
                    recommendationQuickFilters = msg.quickFilters,
                )
            }
        }
    }
}
