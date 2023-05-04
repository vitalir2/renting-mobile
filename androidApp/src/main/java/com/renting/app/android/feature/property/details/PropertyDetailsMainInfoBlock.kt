package com.renting.app.android.feature.property.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails.MainInfo
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetailsPreviews

@Composable
fun PropertyDetailsMainInfoBlock(mainInfo: MainInfo) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = mainInfo.price,
            style = MaterialTheme.typography.h6,
        )
        Gap(12.dp)
        when (mainInfo) {
            is MainInfo.Apartment -> ApartmentMainFeatures(mainInfo)
            is MainInfo.FamilyHouse -> FamilyHouseMainFeatures(mainInfo)
            is MainInfo.Land -> LandMainFeatures(mainInfo)
        }
    }
}

@Composable
private fun LandMainFeatures(mainInfo: MainInfo) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Area:",
            style = MaterialTheme.typography.h6,
        )
        Gap(8.dp)
        Text(
            text = mainInfo.area,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
private fun FamilyHouseMainFeatures(
    mainInfo: MainInfo.FamilyHouse,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MainFeaturesSpacing, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MainInfoFeature(
            title = mainInfo.area,
            subtitle = "area",
        )
        MainInfoFeature(
            title = mainInfo.renovationType,
            subtitle = "renovation",
        )
        MainInfoFeature(
            title = mainInfo.numberOfRooms,
            subtitle = "total",
        )
    }
}

@Composable
private fun ApartmentMainFeatures(mainInfo: MainInfo.Apartment) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MainFeaturesSpacing, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MainInfoFeature(
            title = mainInfo.area,
            subtitle = "area",
        )
        MainInfoFeature(
            title = mainInfo.floor,
            subtitle = "floor",
        )
        MainInfoFeature(
            title = mainInfo.numberOfRooms,
            subtitle = "total",
        )
    }
}

@Composable
private fun MainInfoFeature(
    title: String,
    subtitle: String,
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.secondaryVariant,
        )
    }
}

private val MainFeaturesSpacing = 16.dp

@Preview
@Composable
private fun PropertyDetailsMainInfoBlockPreview() {
    RentingPreviewContainer {
        PropertyDetailsMainInfoBlock(
            mainInfo = ComponentPropertyDetailsPreviews.apartment.mainInfo,
        )
    }
}
