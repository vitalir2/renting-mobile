package com.renting.app.feature.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.renting.app.feature.filters.FiltersComponent
import com.renting.app.feature.home.HomeComponent
import com.renting.app.feature.login.component.LoginComponent
import com.renting.app.feature.property.details.ui.PropertyDetailsComponent
import com.renting.app.feature.registration.component.RegistrationComponent
import com.renting.app.feature.search.results.component.SearchResultsComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Login(val component: LoginComponent) : Child
        data class Registration(val component: RegistrationComponent) : Child
        data class Home(val component: HomeComponent) : Child
        data class PropertyDetails(val component: PropertyDetailsComponent) : Child
        data class Filters(val component: FiltersComponent) : Child
        data class SearchResults(val component: SearchResultsComponent) : Child
    }
}
