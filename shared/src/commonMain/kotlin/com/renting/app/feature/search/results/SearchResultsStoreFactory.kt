package com.renting.app.feature.search.results

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.core.monad.Either
import com.renting.app.core.monad.right
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.search.results.SearchResultsStore.Intent
import com.renting.app.feature.search.results.SearchResultsStore.SearchState
import com.renting.app.feature.search.results.SearchResultsStore.State
import kotlinx.coroutines.launch

internal class SearchResultsStoreFactory(
    private val initQuery: String,
    private val storeFactory: StoreFactory,
) {

    fun create(): SearchResultsStore =
        object : SearchResultsStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "SearchResults",
            initialState = State(
                query = initQuery,
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = {
                ExecutorImpl(
                    searchRepository = object : SearchRepository {
                        // TODO Replace by the real one
                        override suspend fun search(query: String): Either<Exception, List<PropertySnippet>> {
                            return emptyList<PropertySnippet>().right()
                        }
                    }
                )
            },
            reducer = ReducerImpl(),
        ) {}

    private sealed interface Action {
        object InitSearch : Action
    }

    private sealed interface Msg {
        object SearchStarted : Msg
        object SearchError : Msg
        data class PropertySnippets(val snippets: List<PropertySnippet>) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.InitSearch)
        }
    }

    private class ExecutorImpl(
        private val searchRepository: SearchRepository,
    ) : CoroutineExecutor<Intent, Action, State, Msg, Nothing>() {

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.InitSearch -> searchSnippets(getState().query)
            }
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.ApplyFilter -> {
                    // TODO handle filters
                }
                is Intent.ResetFilters -> {
                    // TODO handle filters
                }
                is Intent.SearchSnippets -> {
                    searchSnippets(getState().query)
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
            }
        }
    }
}
