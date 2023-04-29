package com.renting.app.feature.property.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory

internal class DefaultPropertyDetailsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val navigateBack: () -> Unit,
) : PropertyDetailsComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        PropertyDetailsStoreFactory(
            storeFactory = storeFactory,
        ).create()
    }

    override fun onBackButtonClick() {
        navigateBack()
    }
}
