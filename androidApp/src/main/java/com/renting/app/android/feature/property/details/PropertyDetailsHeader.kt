package com.renting.app.android.feature.property.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Badge
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.RentingImage
import com.renting.app.android.core.uikit.RentingImagePlaceholder
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.core.model.Image
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetailsPreviews

@Composable
fun PropertyDetailsHeader(
    images: List<Image>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (images.isEmpty()) {
            RentingImagePlaceholder(
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            ImagesCarousel(images)
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ImagesCarousel(images: List<Image>) {
    val pagerState = rememberPagerState()

    Box {
        HorizontalPager(
            pageCount = images.size,
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            key = { index -> images[index].id },
        ) { index ->
            PagerPage(
                image = images[index],
            )
        }
        ImagesCounter(
            pageNumber = pagerState.currentPage + 1,
            imagesCount = images.size,
            modifier = Modifier.align(Alignment.BottomEnd),
        )
    }
}

@Composable
private fun PagerPage(
    image: Image,
) {
    RentingImage(
        image = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop,
        error = {
            RentingImagePlaceholder()
        },
    )
}

@Composable
private fun ImagesCounter(
    pageNumber: Int,
    imagesCount: Int,
    modifier: Modifier = Modifier,
) {
    Badge(
        modifier = modifier
            .padding(8.dp),
        backgroundColor = Color.Black.copy(
            alpha = 0.2f,
        ),
        contentColor = Color.White,
    ) {
        Text(
            text = "$pageNumber of $imagesCount",
            style = MaterialTheme.typography.caption,
        )
    }
}

@Preview
@Composable
private fun PropertyDetailsHeaderPreview() {
    RentingPreviewContainer {
        PropertyDetailsHeader(
            images = ComponentPropertyDetailsPreviews.familyHouse.images,
        )
    }
}
