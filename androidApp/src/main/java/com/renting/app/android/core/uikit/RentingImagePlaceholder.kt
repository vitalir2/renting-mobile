package com.renting.app.android.core.uikit

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.R

@Composable
fun RentingImagePlaceholder(
    modifier: Modifier = Modifier,
    @DrawableRes placeholderId: Int = R.drawable.renting_logo_full,
) {
    // TODO replace by real placeholder
    Image(
        painter = painterResource(id = placeholderId),
        contentDescription = null,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun RentingImagePlaceholderPreview() {
    RentingPreviewContainer {
        RentingImagePlaceholder(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
