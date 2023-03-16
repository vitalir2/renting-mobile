package com.renting.app.android.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.brandbook.RentingTheme
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingButton
import com.renting.app.feature.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {
    HomeScreen(
        onButtonClick = component::logout,
    )
}

@Composable
private fun HomeScreen(
    onButtonClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Home screen",
                style = MaterialTheme.typography.h5,
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
}

@Preview
@Composable
private fun HomeScreenPreview() {
    RentingTheme {
        HomeScreen(
            onButtonClick = {},
        )
    }
}
