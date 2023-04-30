package com.renting.app.feature.property.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.feature.property.PropertyGraph
import com.renting.app.feature.property.model.OfferId
import com.renting.app.feature.property.model.PropertyId

internal class DefaultPropertyDetailsComponent(
    offerId: OfferId,
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    propertyGraph: PropertyGraph,
    private val navigateBack: () -> Unit,
) : PropertyDetailsComponent, ComponentContext by componentContext, PropertyGraph by propertyGraph {

    private val store = instanceKeeper.getStore {
        PropertyDetailsStoreFactory(
            offerId = offerId,
            storeFactory = storeFactory,
            propertyRepository = propertyRepository,
        ).create()
    }

    override fun onBackButtonClick() {
        navigateBack()
    }
}
