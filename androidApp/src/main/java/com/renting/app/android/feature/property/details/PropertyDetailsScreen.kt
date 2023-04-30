package com.renting.app.android.feature.property.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.details.ui.PropertyDetailsComponent
import com.renting.app.feature.property.details.ui.PropertyDetailsComponent.Model
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails

@Composable
fun PropertyDetailsScreen(component: PropertyDetailsComponent) {
    val models = component.models.subscribeAsState()

    PropertyDetailsScreen(
        model = models.value,
    )
}

@Composable
private fun PropertyDetailsScreen(
    model: Model,
) {
    when (model) {
        is Model.Loading -> LoadingPropertyDetailsScreen()
        is Model.PropertyDetailsLoaded -> LoadedPropertyDetailsScreen(
            details = model.details,
        )
    }
}

@Composable
private fun LoadedPropertyDetailsScreen(
    details: ComponentPropertyDetails,
) {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 4.dp)) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
                .align(Alignment.TopCenter),
        ) {
            PropertyDetailsHeader()
            PropertyDetailsMainInfoBlock(
                mainInfo = details.mainInfo,
            )
            PropertyDetailsLocationBlock()
            PropertyDetailsOwnerBlock()
            PropertyDetailsDescriptionBlock()
        }
        PropertyDetailsBookingFooter(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun LoadingPropertyDetailsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun PropertyDetailsScreenPreview() {
    RentingPreviewContainer {
        PropertyDetailsScreen(
            model = Model.PropertyDetailsLoaded(
                details = ComponentPropertyDetails.preview,
            ),
        )
    }
}
