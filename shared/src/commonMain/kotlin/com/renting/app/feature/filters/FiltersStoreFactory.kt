package com.renting.app.feature.filters

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.renting.app.feature.filters.FiltersStore.Intent
import com.renting.app.feature.filters.FiltersStore.State

class FiltersStoreFactory(
    private val storeFactory: StoreFactory,
) {

    private fun createReducer() = Reducer<State, Msg> { msg ->
        when (msg) {
            is Msg.SelectToggle -> copy(
                filterGroups = filterGroups.map { group ->
                    val updatedFilters = group.filters.map { filter ->
                        if (filter is PropertyTypeFilter && filter.id == msg.id) {
                            filter.setActiveToggle(msg.name)
                        } else {
                            filter
                        }
                    }
                    group.copy(filters = updatedFilters)
                }
            )
            is Msg.ChangeRangeFilter -> copy(
                filterGroups = filterGroups.map { group ->
                    val updatedFilters = group.filters.map { filter ->
                        if (filter is PricePropertyFilter && filter.id == msg.id) {
                            filter.copy(range = msg.range)
                        } else {
                            filter
                        }
                    }
                    group.copy(filters = updatedFilters)
                },
            )
            is Msg.SelectFilterValue -> copy(
                filterGroups = filterGroups.map { group ->
                    val updatedFilters = group.filters.map { filter ->
                        if (filter is PropertyLocationChooser && filter.id == msg.id) {
                            filter.copy(selectedValue = msg.value)
                        } else {
                            filter
                        }
                    }
                    group.copy(filters = updatedFilters)
                },
            )
        }
    }

    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): FiltersStore =
        object : FiltersStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "Filters",
            initialState = State(),
            executorFactory = coroutineExecutorFactory<Intent, Nothing, State, Msg, Nothing> {
                onIntent<Intent.SelectToggle> { intent ->
                    dispatch(
                        Msg.SelectToggle(
                            id = intent.id,
                            name = intent.name,
                        )
                    )
                }
                onIntent<Intent.ChangeRangeFilter> { intent ->
                    dispatch(
                        Msg.ChangeRangeFilter(
                            id = intent.id,
                            range = intent.range,
                        )
                    )
                }
                onIntent<Intent.SelectFilterValue> { intent ->
                    dispatch(
                        Msg.SelectFilterValue(
                            id = intent.id,
                            value = intent.value,
                        )
                    )
                }
            },
            reducer = createReducer(),
        ) {}

    private sealed interface Msg {
        data class SelectToggle(
            val id: String,
            val name: String,
        ) : Msg

        data class ChangeRangeFilter(
            val id: String,
            val range: IntRange,
        ) : Msg

        class SelectFilterValue(
            val id: String,
            val value: String,
        ) : Msg
    }
}
