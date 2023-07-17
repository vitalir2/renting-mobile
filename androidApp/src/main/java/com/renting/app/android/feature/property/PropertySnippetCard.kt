package com.renting.app.android.feature.property

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.R
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingImage
import com.renting.app.android.core.uikit.RentingImagePlaceholder
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.PropertySnippet

@Composable
fun PropertySnippetCard(
    snippet: PropertySnippet,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val isPreviewEnabled = LocalInspectionMode.current
    Surface(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(16.dp),
            )
            .clickable { onClick() }
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background),
        ) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .clip(RoundedCornerShape(16.dp))
            if (isPreviewEnabled) {
                Image(
                    painter = painterResource(R.drawable.renting_logo_full),
                    contentDescription = null,
                    modifier = imageModifier,
                )
            } else {
                // TODO handle errors / loading
                RentingImage(
                    image = snippet.image,
                    contentDescription = "Property $snippet", // TODO make content description better
                    modifier = imageModifier,
                    error = {
                        RentingImagePlaceholder(modifier = imageModifier)
                    },
                    contentScale = ContentScale.Crop,
                )
            }
            Gap(8.dp)
            Text(
                text = "${snippet.price} ₽",
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
            )
            Gap(8.dp)
            Text(
                text = snippet.location,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun PropertySnippetCardPreview() {
    RentingPreviewContainer {
        Box(modifier = Modifier
            .fillMaxSize(0.85f)
            .background(Color.Gray)) {
            PropertySnippetCard(
                snippet = PropertySnippet.preview,
                modifier = Modifier
                    .align(Alignment.Center),
                onClick = {},
            )
        }
    }
}
