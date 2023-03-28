package com.renting.app.feature.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.renting.app.core.utils.stateAsValue
import kotlinx.coroutines.launch

internal class DefaultHomeComponent(
    storeFactory: StoreFactory,
    componentContext: ComponentContext,
    homeGraph: HomeGraph,
    onLoggedOutSuccessfully: () -> Unit,
) : HomeComponent, ComponentContext by componentContext, HomeGraph by homeGraph {

    private val store =
        instanceKeeper.getStore {
            HomeStoreFactory(
                storeFactory = storeFactory,
                authRepository = authRepository,
                userRepository = userRepository,
            ).create()
        }

    init {
        coroutineScope.launch {
            store.labels.collect { label ->
                when (label) {
                    is HomeStore.Label.LoggedOutSuccessfully -> onLoggedOutSuccessfully()
                }
            }
        }
    }

    override val models: Value<HomeComponent.Model> = store.stateAsValue()
        .map(HomeStoreMappers.stateToModel)

    override fun logout() {
        store.accept(HomeStore.Intent.Logout)
    }
}
