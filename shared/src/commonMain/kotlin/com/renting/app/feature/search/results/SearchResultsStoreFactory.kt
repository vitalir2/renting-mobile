package com.renting.app.feature.search.results

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.renting.app.feature.search.results.SearchResultsStore.Intent
import com.renting.app.feature.search.results.SearchResultsStore.State

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
            executorFactory = {
                ExecutorImpl()
            },
            reducer = ReducerImpl(),
        ) {}

    private sealed interface Action

    private sealed interface Msg

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Nothing>()

    private class ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return this
        }
    }
}
