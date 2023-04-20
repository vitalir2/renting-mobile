package com.renting.app.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.renting.app.feature.filters.DefaultFiltersComponent
import com.renting.app.feature.filters.FiltersComponent
import com.renting.app.feature.home.DefaultHomeComponent
import com.renting.app.feature.home.HomeComponent
import com.renting.app.feature.login.component.DefaultLoginComponent
import com.renting.app.feature.login.component.LoginComponent
import com.renting.app.feature.property.details.DefaultPropertyDetailsComponent
import com.renting.app.feature.property.details.PropertyDetailsComponent
import com.renting.app.feature.registration.component.DefaultRegistrationComponent
import com.renting.app.feature.registration.component.RegistrationComponent
import com.renting.app.feature.root.component.RootComponent.Child
import com.renting.app.feature.root.di.RootGraph
import com.renting.app.feature.search.results.DefaultSearchResultsComponent
import com.renting.app.feature.search.results.SearchResultsComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val rootGraph: RootGraph,
) : RootComponent, ComponentContext by componentContext, RootGraph by rootGraph {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = if (rootGraph.loginGraph.authRepository.isLoggedIn()) {
                Configuration.Home
            } else {
                Configuration.Login
            },
            handleBackButton = true,
            childFactory = ::createChild,
        )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        is Configuration.Login -> Child.Login(
            createLoginComponent(componentContext)
        )
        is Configuration.Home -> Child.Home(
            createHomeComponent(componentContext)
        )
        is Configuration.Registration -> Child.Registration(
            createRegistrationComponent(componentContext)
        )
        is Configuration.PropertyDetails -> Child.PropertyDetails(
            createPropertyDetailsComponent(componentContext)
        )
        is Configuration.Filters -> Child.Filters(
            createFiltersComponent(componentContext)
        )
        is Configuration.SearchResults -> Child.SearchResults(
            createSearchResultsComponent(componentContext)
        )
    }

    private fun createSearchResultsComponent(
        componentContext: ComponentContext,
    ): SearchResultsComponent {
        return DefaultSearchResultsComponent(
            componentContext = componentContext,
            openFullFilters = { navigation.bringToFront(Configuration.Filters) },
            openPropertyDetails = { id -> navigation.push(Configuration.PropertyDetails(id)) },
            navigateBack = { navigation.pop() },
        )
    }

    private fun createFiltersComponent(
        componentContext: ComponentContext,
    ): FiltersComponent {
        return DefaultFiltersComponent(
            componentContext = componentContext,
        )
    }

    private fun createPropertyDetailsComponent(
        componentContext: ComponentContext,
    ): PropertyDetailsComponent {
        return DefaultPropertyDetailsComponent(
            componentContext = componentContext,
            navigateBack = { navigation.pop() },
        )
    }

    private fun createRegistrationComponent(componentContext: ComponentContext): RegistrationComponent {
        return DefaultRegistrationComponent(
            componentContext = componentContext,
            storeFactory = storeFactory,
            registrationGraph = registrationGraph,
            onRegistrationCompleted = {
                navigation.replaceCurrent(Configuration.Home)
            },
            openLoginScreen = {
                navigation.replaceCurrent(Configuration.Login)
            },
        )
    }

    private fun createHomeComponent(componentContext: ComponentContext): HomeComponent {
        return DefaultHomeComponent(
            componentContext = componentContext,
            storeFactory = storeFactory,
            homeGraph = homeGraph,
            onLoggedOutSuccessfully = {
                navigation.replaceCurrent(
                    configuration = Configuration.Login,
                )
            },
            openFullFilters = { navigation.bringToFront(Configuration.Filters) },
            openSearchResults = { query -> navigation.push(Configuration.SearchResults(query)) },
            openRecommendation = { id ->
                navigation.push(Configuration.PropertyDetails(id))
            },
        )
    }

    private fun createLoginComponent(
        componentContext: ComponentContext,
    ): LoginComponent = DefaultLoginComponent(
        componentContext = componentContext,
        storeFactory = storeFactory,
        loginGraph = loginGraph,
        openMainScreen = {
            navigation.replaceCurrent(Configuration.Home)
        },
        openRegistrationScreen = {
            navigation.replaceCurrent(Configuration.Registration)
        }
    )

    private sealed class Configuration : Parcelable {
        @Parcelize
        data class PropertyDetails(val propertyId: Long) : Configuration()

        @Parcelize
        data class SearchResults(val query: String) : Configuration()

        @Parcelize
        object Login : Configuration()

        @Parcelize
        object Registration : Configuration()

        @Parcelize
        object Home : Configuration()

        @Parcelize
        object Filters : Configuration()
    }
}
