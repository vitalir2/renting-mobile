package com.renting.app.android.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.android.feature.auth.previewData
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.feature.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {
    val models = component.models.subscribeAsState()

    HomeScreen(
        model = models.value,
        onButtonClick = component::logout,
    )
}

@Composable
private fun HomeScreen(
    model: HomeComponent.Model,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        HomeProfileBar(userInfo = model.userInfo)
        RentingButton(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Logout")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    RentingPreviewContainer {
        HomeScreen(
            model = HomeComponent.Model(
                userInfo = UserInfo.previewData,
            ),
            onButtonClick = {},
        )
    }
}
