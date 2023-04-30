package com.renting.app.feature.property.details.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.core.utils.stateAsValue
import com.renting.app.feature.property.PropertyGraph
import com.renting.app.feature.property.details.domain.PropertyDetailsStoreFactory
import com.renting.app.feature.property.model.OfferId

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

    override val models: Value<PropertyDetailsComponent.Model> = store.stateAsValue()
        .map(PropertyDetailsMappers.stateToModel)

    override fun onBackButtonClick() {
        navigateBack()
    }
}
