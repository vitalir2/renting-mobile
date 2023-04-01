package com.renting.app.android.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.android.feature.auth.previewData
import com.renting.app.android.feature.property.PropertySnippetsGrid
import com.renting.app.android.feature.property.preview
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.feature.home.HomeComponent
import com.renting.app.feature.property.PropertySnippet

@Composable
fun HomeScreen(component: HomeComponent) {
    val models = component.models.subscribeAsState()

    HomeScreen(
        model = models.value,
        onRecommendationClick = component::onRecommendationClicked,
        onButtonClick = component::logout,
    )
}

@Composable
private fun HomeScreen(
    model: HomeComponent.Model,
    onRecommendationClick: (id: Long) -> Unit,
    onButtonClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(HomeScreenPadding),
    ) {
        PropertySnippetsGrid(
            snippets = model.recommendations,
            onSnippetClick = onRecommendationClick,
            modifier = Modifier
                .fillMaxSize(),
            prependedItems = {
                item(key = "Toolbar", span = { GridItemSpan(maxLineSpan) }) {
                    HomeProfileBar(userInfo = model.userInfo)
                }
                item(key = "Recommendations title", span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Our Recommendation",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(8.dp))
                }
            },
        )
        RentingButton(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
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
                recommendations = List(5) { PropertySnippet.preview },
            ),
            onRecommendationClick = {},
            onButtonClick = {},
        )
    }
}
