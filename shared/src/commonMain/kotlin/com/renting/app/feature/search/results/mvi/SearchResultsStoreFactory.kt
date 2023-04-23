package com.renting.app.feature.search.results.mvi

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.monad.Either
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.SearchRepository
import com.renting.app.feature.search.results.SearchState
import com.renting.app.feature.search.results.mvi.SearchResultsStore.Intent
import com.renting.app.feature.search.results.mvi.SearchResultsStore.State
import kotlinx.coroutines.launch

internal class SearchResultsStoreFactory(
    private val initQuery: String,
    private val storeFactory: StoreFactory,
    private val searchRepository: SearchRepository,
) {

    fun create(): SearchResultsStore =
        object : SearchResultsStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "SearchResults",
            initialState = State(),
            bootstrapper = BootstrapperImpl(
                initQuery = initQuery,
            ),
            executorFactory = {
                ExecutorImpl(
                    searchRepository = searchRepository,
                )
            },
            reducer = ReducerImpl(),
        ) {}

    private sealed interface Action {
        object InitQuickFilters : Action
        data class InitSearch(val query: String) : Action
    }

    private sealed interface Msg {
        object SearchStarted : Msg
        object SearchError : Msg
        data class PropertySnippets(val snippets: List<PropertySnippet>) : Msg
        data class UpdatedQuickTypeFilters(val filters: PropertyTypeQuickFilters) : Msg
    }

    private class BootstrapperImpl(
        private val initQuery: String,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.InitSearch(initQuery))
            dispatch(Action.InitQuickFilters)
        }
    }

    private class ExecutorImpl(
        private val searchRepository: SearchRepository,
    ) : CoroutineExecutor<Intent, Action, State, Msg, Nothing>() {

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.InitSearch -> searchSnippets(action.query)
                is Action.InitQuickFilters -> initQuickFilters()
            }
        }

        private fun initQuickFilters() {
            val quickFilters = PropertyTypeQuickFilters.filtersOrder
                .map(::PropertyTypeQuickFilter)
                .let(::PropertyTypeQuickFilters)
            dispatch(Msg.UpdatedQuickTypeFilters(quickFilters))
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.ApplyQuickFilter -> {
                    val filters = getState().quickFilters ?: return
                    val updatedFilters = filters.setActive(intent.type)
                    dispatch(Msg.UpdatedQuickTypeFilters(updatedFilters))
                }
                is Intent.ResetFilters -> {
                    val filters = getState().quickFilters ?: return
                    val updatedFilters = filters.clearSelection()
                    dispatch(Msg.UpdatedQuickTypeFilters(updatedFilters))
                }
                is Intent.SearchSnippets -> {
                    searchSnippets(intent.query)
                }
            }
        }

        private fun searchSnippets(query: String) {
            scope.launch {
                dispatch(Msg.SearchStarted)
                when (val result = searchRepository.search(query)) {
                    is Either.Left -> {
                        // TODO handle error the right way
                        dispatch(Msg.SearchError)
                    }
                    is Either.Right -> {
                        dispatch(Msg.PropertySnippets(result.value))
                    }
                }
            }
        }
    }

    private class ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.PropertySnippets -> copy(
                    searchState = if (msg.snippets.isEmpty()) {
                        SearchState.EmptyResults
                    } else {
                        SearchState.Results(msg.snippets)
                    }
                )
                is Msg.SearchStarted -> copy(
                    searchState = SearchState.Loading,
                )
                is Msg.SearchError -> copy(
                    searchState = SearchState.Error,
                )
                is Msg.UpdatedQuickTypeFilters -> copy(
                    quickFilters = msg.filters,
                )
            }
        }
    }
}
