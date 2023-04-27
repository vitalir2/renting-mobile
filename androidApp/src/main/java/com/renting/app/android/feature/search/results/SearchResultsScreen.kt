package com.renting.app.android.feature.search.results

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.R
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingButtonAction
import com.renting.app.android.core.uikit.RentingErrorPlaceholder
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.android.feature.property.PropertySnippetsGrid
import com.renting.app.android.feature.property.preview
import com.renting.app.android.feature.property.quickfilter.PropertyTypeQuickFilters
import com.renting.app.android.feature.search.SearchInput
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.input.DummySearchInputComponent
import com.renting.app.feature.search.input.SearchInputComponent
import com.renting.app.feature.search.results.component.SearchResultsComponent
import com.renting.app.feature.search.results.SearchState

@Composable
fun SearchResultsScreen(component: SearchResultsComponent) {
    val model by component.models.subscribeAsState()

    SearchResultsScreen(
        model = model,
        searchInputComponent = component.searchInputComponent,
        onFiltersSelected = component::onQuickFilterToggled,
        onSelectedFiltersCleared = component::onResetQuickFiltersSelected,
        onSnippetClicked = component::onSnippetClicked,
        onNavigationBackRequested = component::onNavigateBackRequested,
        onErrorPlaceholderActionRequested = component.searchInputComponent::onSearchClicked,
    )
}

@Composable
private fun SearchResultsScreen(
    model: SearchResultsComponent.Model,
    searchInputComponent: SearchInputComponent,
    onFiltersSelected: (type: PropertyType) -> Unit,
    onSelectedFiltersCleared: () -> Unit,
    onSnippetClicked: (id: Long) -> Unit,
    onNavigationBackRequested: () -> Unit,
    onErrorPlaceholderActionRequested: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(24.dp)
                    .clickable { onNavigationBackRequested() }
            )
            SearchInput(
                component = searchInputComponent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }
        PropertyTypeQuickFilters(
            quickFilters = model.quickFilters,
            modifier = Modifier
                .fillMaxWidth(),
            onFilterSelected = onFiltersSelected,
            onSelectedFiltersCleared = onSelectedFiltersCleared,
        )
        Gap(16.dp)
        SearchScreen(
            state = model.searchState,
            onSnippetClicked = onSnippetClicked,
            onErrorPlaceholderActionRequested = onErrorPlaceholderActionRequested,
        )
    }
}

@Composable
private fun SearchScreen(
    state: SearchState,
    onSnippetClicked: (id: Long) -> Unit,
    onErrorPlaceholderActionRequested: () -> Unit,
) {
    Column {
        SearchScreenHeader(state)
        Gap(16.dp)
        SearchScreenContent(state, onSnippetClicked, onErrorPlaceholderActionRequested)
    }
}

@Composable
private fun SearchScreenHeader(state: SearchState) {
    val snippetsCount = when (state) {
        is SearchState.EmptyResults -> 0
        is SearchState.Results -> state.snippets.size
        is SearchState.Error, is SearchState.Loading -> null
    } ?: return

    Text(
        text = "$snippetsCount found",
        style = MaterialTheme.typography.h6,
    )
}

@Composable
private fun SearchScreenContent(
    state: SearchState,
    onSnippetClicked: (id: Long) -> Unit,
    onErrorPlaceholderActionRequested: () -> Unit,
) {
    when (state) {
        is SearchState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                CircularProgressIndicator()
            }
        }
        is SearchState.Results -> {
            val scrollState = rememberScrollState()

            PropertySnippetsGrid(
                snippets = state.snippets,
                onSnippetClick = onSnippetClicked,
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(16.dp),
                    ),
            )
        }
        is SearchState.EmptyResults -> {
            EmptyResultsPlaceholder()
        }
        is SearchState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                RentingErrorPlaceholder(
                    action = RentingButtonAction(
                        title = "Retry",
                        handler = onErrorPlaceholderActionRequested,
                    ),
                )
            }
        }
    }
}

@Composable
private fun EmptyResultsPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.search_results_empty),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
            )
            Text(
                text = "Not found",
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
            )
            Gap(8.dp)
            Text(
                text = "Sorry, the keyword you entered cannot be found," +
                        " please check again or search with another keyword",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun SearchResultsScreenPreview() {
    RentingPreviewContainer {
        SearchResultsScreen(
            model = SearchResultsComponent.Model(
                quickFilters = PropertyTypeQuickFilters.filtersOrder
                    .map(::PropertyTypeQuickFilter)
                    .let(::PropertyTypeQuickFilters),
                searchState = SearchState.Results(
                    snippets = List(5) { PropertySnippet.preview },
                ),
            ),
            searchInputComponent = DummySearchInputComponent(),
            onFiltersSelected = {},
            onSelectedFiltersCleared = {},
            onSnippetClicked = {},
            onNavigationBackRequested = {},
            onErrorPlaceholderActionRequested = {},
        )
    }
}
