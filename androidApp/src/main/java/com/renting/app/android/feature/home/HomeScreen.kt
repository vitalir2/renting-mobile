package com.renting.app.android.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.android.feature.auth.previewData
import com.renting.app.android.feature.property.PropertySnippetsGrid
import com.renting.app.android.feature.property.PropertyTypeQuickFilters
import com.renting.app.android.feature.property.preview
import com.renting.app.android.feature.search.SearchInput
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.feature.home.HomeComponent
import com.renting.app.feature.property.PropertySnippet
import com.renting.app.feature.property.PropertyType
import com.renting.app.feature.property.PropertyTypeQuickFilter
import com.renting.app.feature.property.PropertyTypeQuickFilters
import com.renting.app.feature.search.DummySearchInputComponent
import com.renting.app.feature.search.SearchInputComponent

@Composable
fun HomeScreen(component: HomeComponent) {
    val models = component.models.subscribeAsState()

    HomeScreen(
        model = models.value,
        searchInputComponent = component.searchInput,
        onRecommendationClick = component::onRecommendationClicked,
        onButtonClick = component::logout,
        onQuickFilterSelected = component::onTypeQuickFilterClicked,
        onSelectedFiltersCleared = component::clearTypeQuickFiltersSelection,
    )
}

@Composable
private fun HomeScreen(
    model: HomeComponent.Model,
    searchInputComponent: SearchInputComponent,
    onRecommendationClick: (id: Long) -> Unit,
    onButtonClick: () -> Unit,
    onQuickFilterSelected: (PropertyType) -> Unit,
    onSelectedFiltersCleared: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(HomeScreenPadding)
            .verticalScroll(scrollState),
    ) {
        HomeProfileBar(userInfo = model.userInfo)
        Gap(8.dp)
        SearchInput(
            component = searchInputComponent,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Gap(24.dp)
        Text(
            text = "Our Recommendation",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
        )
        Gap(12.dp)
        PropertyTypeQuickFilters(
            quickFilters = model.recommendationQuickFilters,
            onFilterSelected = onQuickFilterSelected,
            onSelectedFiltersCleared = onSelectedFiltersCleared,
        )
        Gap(8.dp)
        PropertySnippetsGrid(
            snippets = model.recommendations,
            onSnippetClick = onRecommendationClick,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(16.dp),
                ),
        )
        Gap(16.dp)
        RentingButton(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Logout")
        }
    }
}

private val HomeScreenPadding = 24.dp

@Preview
@Composable
private fun HomeScreenPreview() {
    RentingPreviewContainer {
        HomeScreen(
            model = HomeComponent.Model(
                userInfo = UserInfo.previewData,
                recommendations = List(3) { PropertySnippet.preview },
                recommendationQuickFilters = PropertyTypeQuickFilters(
                    list = PropertyType.values().map { PropertyTypeQuickFilter(type = it) },
                ),
            ),
            searchInputComponent = DummySearchInputComponent(),
            onRecommendationClick = {},
            onButtonClick = {},
            onQuickFilterSelected = {},
            onSelectedFiltersCleared = {},
        )
    }
}
